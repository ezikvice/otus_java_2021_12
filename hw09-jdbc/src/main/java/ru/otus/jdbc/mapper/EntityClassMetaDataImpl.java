package ru.otus.jdbc.mapper;

import ru.otus.core.annotations.Id;
import ru.otus.crm.model.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> clazz;

    public EntityClassMetaDataImpl(T clazz) {
        this.clazz = (Class<T>) clazz;
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        List<Field> allFields = getAllFields();
        for (var constructor : clazz.getConstructors()) {
            if (constructor.getParameters().length == allFields.size()) {
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }

    @Override
    public Field getIdField() {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .get();
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.stream(clazz.getDeclaredFields()).toList();
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .toList();
    }

    public static void main(String[] args) {
        Manager m = new Manager(1L, "label", "param1");
        var md = new EntityClassMetaDataImpl(Manager.class);
        Field idField = md.getIdField();
        String name = md.getName();
        var allFields = md.getAllFields();
        var fieldsWithoutId = md.getFieldsWithoutId();
        var constructor = md.getConstructor();
    }
}
