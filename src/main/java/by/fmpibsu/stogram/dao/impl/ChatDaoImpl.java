package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatDaoImpl implements ChatDao {
    private final ArrayList<Chat> chatStorage = new ArrayList<>();

    @Override
    public Optional<Chat> get(long id) {
        return id >= 0 && id < chatStorage.size()
                ? Optional.of(chatStorage.get((int) id))
                : Optional.empty();
    }

    @Override
    public List<Chat> getAllWith(long memberId) {
        var res = new ArrayList<Chat>();
        for (var chat : chatStorage) {
            if (chat.getMemberIds().contains(memberId)) {
                res.add(chat);
            }
        }
        return res;
    }

    @Override
    public void save(Chat chat) {
        chatStorage.add(chat);
    }

    @Override
    public void delete(Chat chat) {
        chatStorage.remove(chat);
    }
}
