package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorSwapFields implements Processor {

    @Override
    public Message process(Message message) {
        var newField11Value = message.getField12();
        var newField12Value = message.getField11();
        return message.toBuilder().field11(newField11Value).field12(newField12Value).build();
    }
}
