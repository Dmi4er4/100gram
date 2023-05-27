package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatDaoImpl implements ChatDao {
    private final JdbcTemplate jdbcTemplate;

    public ChatDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Chat> get(long id) {
        List<Chat> chats = jdbcTemplate.query("SELECT * FROM chat WHERE id = " + id, (rs, rowNum) -> {
            return new Chat(rs.getInt(1), rs.getDate(2));
        });
        return id >= 0 && id < chats.size()
                ? Optional.of(chats.get(0))
                : Optional.empty();
    }

    @Override
    public List<Chat> getAllWith(long memberId) {
        String sql = "SELECT * FROM chat";
//        String sql1 = "SELECT DISTINCT chat_id FROM user_chat WHERE user_id = ?";
        RowMapper<Chat> rowMapper = (rs, rowNum) -> {
            Chat chat = new Chat(rs.getInt(1));
        return jdbcTemplate.query(sql,
            chat.set;
        })
        jdbcTemplate.(sql1, memberId);
        var res = new ArrayList<Chat>();
        for (var chat : chatStorage) {
            if (chat.getMemberIds().contains(memberId)) {
                res.add(chat);
            }
        }
        jdbcTemplate.
        return res;
    }

    @Override
    public void save(Chat chat) {
        String sql = "INSERT INTO chat (id, date) VALUES (?, ?)";
        jdbcTemplate.update(sql, chat.getId(), chat.getDateCreated());
    }

    @Override
    public void delete(Chat chat) {
        String sql = "DELETE FROM chat WHERE id = ?";
        jdbcTemplate.update(sql, chat.getId());
    }

    @Override
    public Chat findOrCreateDialog(long id1, long id2) {
        for (var chat : chatStorage) {
            var mem = chat.getMemberIds();
            if (mem.size() == 2 && mem.contains(id1) && mem.contains(id2)) {
                return chat;
            }
        }
        var newChat = new Chat(chatStorage.size());
        newChat.addMember(id1);
        newChat.addMember(id2);
        save(newChat);
        return newChat;
    }
}
