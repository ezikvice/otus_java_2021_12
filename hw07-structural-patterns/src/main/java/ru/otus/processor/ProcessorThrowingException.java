package ru.otus.processor;

import ru.otus.model.Message;

import java.time.LocalDateTime;

public class ProcessorThrowingException implements Processor {
    private final DateTimeProvider dateTimeProvider;

    public ProcessorThrowingException(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        LocalDateTime now = dateTimeProvider.getDate();
        if (now.getSecond() % 2 == 0) {
            throw new RuntimeException("Oops! It`s seems like it's an even second now");
        }
        return null;
    }
}
