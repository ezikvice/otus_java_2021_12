package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private Map<Long, Message> history = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        if (msg != null) {
            Message.Builder builder = msg.toBuilder();
            ObjectForMessage oldField13 = msg.getField13();
            if (oldField13 != null) {
                // TODO: "только проще и надеждее сделать у Message метод clone и тут его вызывать. А внутри clone уже должно быть копирование полей."
                ObjectForMessage newField13 = new ObjectForMessage();
                var field13Data = new ArrayList<String>();
                field13Data.addAll(oldField13.getData());
                newField13.setData(field13Data);
                builder.field13(newField13);
            }
            history.put(msg.getId(), builder.build());
        }
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(history.get(id));
    }
}
