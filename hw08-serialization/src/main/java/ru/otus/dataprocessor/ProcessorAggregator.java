package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        SortedMap<String, Double> processed = new TreeMap<>();
        for (Measurement measurement : data) {
            String name = measurement.getName();
            Double sum = measurement.getValue();
            if (processed.containsKey(name)) {
                sum += processed.get(name);
            }
            processed.put(name, sum);
        }
        return processed;
    }
}
