package by.fmpibsu.stogram.service.impl;

import by.fmpibsu.stogram.dao.MessageDao;
import by.fmpibsu.stogram.entity.Message;
import by.fmpibsu.stogram.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Message sendMessage(long from, long chatId, String text) {
        return messageDao.createMessage(from, chatId, text);
    }

    @Override
    public List<Message> loadChat(long chatId) {
        return messageDao.loadAllFrom(chatId);
    }

    @Override
    public List<Message> fetchNewerMessages(long chatId, Timestamp latest) {
        return messageDao.loadAllAfter(latest, chatId);
    }
}
