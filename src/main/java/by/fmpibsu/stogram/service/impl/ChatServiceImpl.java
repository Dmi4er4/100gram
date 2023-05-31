package by.fmpibsu.stogram.service.impl;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatDao chatDao;

    @Autowired
    public ChatServiceImpl(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @Override
    public List<Chat> getAllWith(long memberId) {
        return chatDao.getAllWith(memberId);
    }

    @Override
    public Chat createChat(List<Long> memberIds) {
        return chatDao.createChat(memberIds);
    }
}
