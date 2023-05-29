package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface ChatDao extends Dao<Chat> {
    Optional<Chat> read(long id);

    List<Chat> getAllWith(long memberId);

    void delete(Chat chat);

    Chat createChat(List<Long> memberIds);
}
