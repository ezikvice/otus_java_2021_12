package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData metaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData metadata) {
        this.metaData = metadata;
    }

    @Override
    public String getSelectAllSql() {
        List<Field> allFields = metaData.getAllFields();
        String allFieldNames = allFields
                .stream()
                .map(Field::getName)
                .collect(Collectors.joining(", ", "", ""));
        String s = String.format("SELECT %1s FROM %2s", allFieldNames, metaData.getName());
        return s;
    }

    @Override
    public String getSelectByIdSql() {
        String table = metaData.getName();
        String id = metaData.getIdField().getName();
        List<Field> allFields = metaData.getAllFields();
        String fields = allFields
                .stream()
                .map(Field::getName)
                .collect(Collectors.joining(", ", "", ""));
        String s = String.format("SELECT %1s FROM %2s WHERE %3s = ?", fields, table, id);
        return s;
    }

    @Override
    public String getInsertSql() {
        String tableName = metaData.getName();
        List<Field> fields = metaData.getFieldsWithoutId();
        String fieldNames = fields
                .stream()
                .map(Field::getName)
                .collect(Collectors.joining(", ", "(", ")"));
        String fieldValues = fields
                .stream()
                .map(field -> "?")
                .collect(Collectors.joining(", ", "(", ")"));
        String s = String.format("INSERT INTO %1s%2s VALUES %3s", tableName, fieldNames, fieldValues);
        return s;
    }

    @Override
    public String getUpdateSql() {
//        TODO: implement
        return null;
    }
}
