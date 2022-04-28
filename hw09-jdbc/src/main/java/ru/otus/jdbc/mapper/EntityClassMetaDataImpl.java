package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final T clazz;

    public EntityClassMetaDataImpl(T clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Constructor<T> getConstructor() {
        return null;
    }

    @Override
    public Field getIdField() {
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return null;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return null;
    }
}
