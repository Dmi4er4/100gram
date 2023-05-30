package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatDao chatDao;

    @Autowired
    public ChatService(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public List<Chat> getAllWith(long memberId) {
        return chatDao.getAllWith(memberId);
    }

    public Chat createChat(List<Long> memberIds) {
        return chatDao.createChat(memberIds);
    }
}
