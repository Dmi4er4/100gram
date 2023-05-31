package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChatService {

    List<Chat> getAllWith(long memberId);

    Chat createChat(List<Long> memberIds);
}
