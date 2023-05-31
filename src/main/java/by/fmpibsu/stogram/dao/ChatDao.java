package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.Chat;

import java.util.List;

public interface ChatDao extends Dao<Chat> {

    List<Chat> getAllWith(long memberId);

    void delete(Chat chat);

    Chat createChat(List<Long> memberIds);
}
