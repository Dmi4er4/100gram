package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.MessageDao;
import by.fmpibsu.stogram.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

public interface MessageService {

    List<Message> loadChat(long chatId);

    List<Message> fetchNewerMessages(long chatId, Timestamp latest);
}
