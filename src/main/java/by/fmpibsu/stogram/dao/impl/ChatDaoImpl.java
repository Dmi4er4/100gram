package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.time.LocalDate;
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
    public Optional<Chat> readById(long id) {
        List<Chat> chats = jdbcTemplate.query("SELECT * FROM chat WHERE id = ?",
                (rs, rowNum) -> new Chat(rs.getInt("id"), rs.getDate("date")), id);
        if (chats.isEmpty()) return Optional.empty();
        var chat = chats.get(0);
        chat.setMemberIds(jdbcTemplate.query("SELECT user_id FROM user_chat WHERE chat_id = ?",
                (rs, rowNum) -> rs.getLong("user_id"), id));
        return Optional.of(chat);
    }

    @Override
    public List<Chat> getAllWith(long memberId) {
        var res = jdbcTemplate.query(
                "SELECT * FROM user_chat WHERE user_id = ?",
                (rs, rowNum) -> new Chat(rs.getLong("chat_id")), memberId);
        for (var chat : res) {
            jdbcTemplate.query("SELECT * FROM user_chat WHERE chat_id = ?", rs -> {
                chat.addMember(rs.getLong("user_id"));
            }, chat.getId());
            jdbcTemplate.query("SELECT * FROM chat WHERE id = ?", rs -> {
                chat.setDateCreated(rs.getDate("date"));
            }, chat.getId());
        }
        return res;
    }

    @Override
    public void delete(Chat chat) {
        jdbcTemplate.update("DELETE FROM chat WHERE id = ?", chat.getId());
        jdbcTemplate.update("DELETE FROM user_chat WHERE chat_id = ?", chat.getId());
    }

    @Override
    public Chat createChat(List<Long> memberIds) {
        var chat = new Chat(memberIds);
        chat.setDateCreated(Date.valueOf(LocalDate.now()));
        jdbcTemplate.query("INSERT INTO chat (date) VALUES (?) RETURNING id",
                rs -> {
                    chat.setId(rs.getInt("id"));
                }, chat.getDateCreated());
        for (var id : memberIds) {
            jdbcTemplate.update(
                    "INSERT INTO user_chat (user_id, chat_id) VALUES (?, ?)",
                    id, chat.getId());
        }
        return chat;
    }
}
