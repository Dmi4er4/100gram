package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatDao {
    Optional<Chat> get(long id);

    List<Chat> getAllWith(long memberId);

    void save(Chat chat);

    void delete(Chat chat);

    Chat findOrCreateDialog(long id1, long id2);
}
