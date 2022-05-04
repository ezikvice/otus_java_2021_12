package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor,
                            EntitySQLMetaData entitySQLMetaData,
                            EntityClassMetaData entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        List initArgs = new ArrayList<>();
        List<Field> fields = entityClassMetaData.getAllFields();
        T t1 = (T) dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    var constructor = entityClassMetaData.getConstructor();
                    try {
//                        TODO: отрефакторить.
                        for (Field field : fields) {
                            initArgs.add(rs.getObject(rs.findColumn(field.getName())));
                        }
                        Object[] objects = initArgs.toArray();
                        T t = (T) constructor.newInstance(objects);
                        return t;
                    } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                return Optional.<T>empty();
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
        return (Optional<T>) t1;
    }

    @Override
    public List<T> findAll(Connection connection) {
        // TODO: implement
        throw new UnsupportedOperationException();
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
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),
                    params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
//        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Connection connection, T client) {
        // TODO: implement
        throw new UnsupportedOperationException();
    }
}
