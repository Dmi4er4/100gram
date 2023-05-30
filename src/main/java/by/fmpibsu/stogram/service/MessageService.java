package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.MessageDao;
import by.fmpibsu.stogram.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Message sendMessage(long from, long chatId, String text) {
        return messageDao.createMessage(from, chatId, text);
    }

    public List<Message> loadChat(long chatId) {
        return messageDao.loadAllFrom(chatId);
    }

    public List<Message> fetchNewerMessages(long chatId, Timestamp latest) {
        return messageDao.loadAllAfter(latest, chatId);
    }
}
