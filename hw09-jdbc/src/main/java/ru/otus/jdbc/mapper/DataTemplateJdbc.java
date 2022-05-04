package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        var initArgsList = new ArrayList<>();
        List<Field> fields = entityClassMetaData.getAllFields();
        var constructor = entityClassMetaData.getConstructor();
        return (Optional<T>) dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    try {
                        for (Field field : fields) {
                            initArgsList.add(rs.getObject(rs.findColumn(field.getName())));
                        }
                        Object[] initArgs = initArgsList.toArray();
                        return constructor.newInstance(initArgs);
                    } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                return Optional.<T>empty();
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        var initArgsList = new ArrayList<>();
        List<Field> fields = entityClassMetaData.getAllFields();
        List<T> objects = new ArrayList<>();
        var constructor = entityClassMetaData.getConstructor();
        dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
            try {
                while (rs.next()) {
                    for (Field field : fields) {
                        initArgsList.add(rs.getObject(rs.findColumn(field.getName())));
                    }
                    Object[] initArgs = initArgsList.toArray();
                    objects.add((T) constructor.newInstance(initArgs));
                }
            } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return Collections.emptyList();
        });
        return objects;
    }

    @Override
    public long insert(Connection connection, T object) {
        List<Object> params = new ArrayList<>();
        List<Field> fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        for (Field f : fieldsWithoutId) {
            try {
                f.setAccessible(true);
                params.add(f.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T object) {
        List<Object> params = new ArrayList<>();
        List<Field> fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        for (Field f : fieldsWithoutId) {
            try {
                f.setAccessible(true);
                params.add(f.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        // добавляем параметр - значение айдишного поля для условия WHERE {ID_FIELD} = ?
        try {
            params.add(entityClassMetaData.getIdField().get(object));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }
}
