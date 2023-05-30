package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.entity.Message;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDao extends Dao<Message> {
    Message createMessage(long senderId, long chatId, String content);

    List<Message> loadAllFrom(long chatId);

    List<Message> loadAllAfter(Timestamp timestamp, long chatId);
}
