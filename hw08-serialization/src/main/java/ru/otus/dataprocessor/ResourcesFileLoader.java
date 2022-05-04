package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;
import ru.otus.model.MeasurementMixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String filename;

    public ResourcesFileLoader(String fileName) {
        this.filename = fileName;
    }

    /**
     * Читает файл, парсит и возвращает список измерений
     *
     * @return список измерений
     */
    @Override
    public List<Measurement> load() {
        // пример с Mixin украден у https://github.com/hgbrown/jackson-mixin-example
        // там же можно глянуть buildMapper() с настройками маппера
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Measurement.class, MeasurementMixin.class);

        List<Measurement> list;
        try (InputStream is = ResourcesFileLoader.class.getClassLoader().getResourceAsStream(this.filename)) {
            list = Arrays.asList(mapper.readValue(is, Measurement[].class));
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
        return list;
    }
}
