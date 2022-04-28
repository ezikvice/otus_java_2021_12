package ru.otus.jdbc.mapper;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData metaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData metadata) {
        this.metaData = metadata;
    }

    @Override
    public String getSelectAllSql() {
        return null;
    }

    @Override
    public String getSelectByIdSql() {
        return null;
    }

    @Override
    public String getInsertSql() {
        return null;
    }

    @Override
    public String getUpdateSql() {
        return null;
    }
}
