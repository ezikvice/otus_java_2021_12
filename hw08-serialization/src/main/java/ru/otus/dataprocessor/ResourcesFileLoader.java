package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.otus.model.Measurement;
import ru.otus.model.MeasurementDeserializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final InputStream is;

    public ResourcesFileLoader(String fileName) {
        this.is = ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName);
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        // прикручен десериализатор, чтобы обойтись без аннотаций в Measurement.java
        // TODO: как можно обойтись без собственного десериализатора?
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Measurement.class, new MeasurementDeserializer());
        mapper.registerModule(module);

        List<Measurement> list;
        try {
            list = Arrays.asList(mapper.readValue(is, Measurement[].class));
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
        return list;
    }
}
