package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.MessageDao;
import by.fmpibsu.stogram.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void sendMessage(long from, long chatId, String text) {
        messageDao.createMessage(from, chatId, text);
    }

    public List<Message> loadChat(long chatId) {
        return messageDao.loadAllFrom(chatId);
    }
}
