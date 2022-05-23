package ru.otus.services;

import java.io.IOException;
import java.util.Map;

public interface TemplateProcessor {
    String getPage(String filename, Map<Object, Object> data) throws IOException;
}
