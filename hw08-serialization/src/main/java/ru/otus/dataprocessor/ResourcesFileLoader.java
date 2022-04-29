package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;
import ru.otus.model.MeasurementMixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final byte[] content;

    public ResourcesFileLoader(String fileName) {
        try(InputStream is = ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            this.content = is.readAllBytes();
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        // пример с Mixin украден у https://github.com/hgbrown/jackson-mixin-example
        // там же можно глянуть buildMapper() с настройками маппера
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Measurement.class, MeasurementMixin.class);

        List<Measurement> list;
        try {
            list = Arrays.asList(mapper.readValue(content, Measurement[].class));
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
        return list;
    }
}
