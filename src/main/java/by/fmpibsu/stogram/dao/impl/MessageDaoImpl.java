package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.MessageDao;
import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageDaoImpl implements MessageDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Message createMessage(long senderId, long chatId, String content) {
        var msg = new Message(senderId, content);
        jdbcTemplate.query (
                "INSERT INTO message (sender_id, chat_id, content, time) " +
                        "VALUES (?, ?, ?, ?) RETURNING id",
                rs -> {
                    msg.setId(rs.getLong("id"));
                },
                senderId,
                chatId,
                content,
                msg.getTime()
        );
        return msg;
    }

    private Message buildMessage(ResultSet rs) throws SQLException {
        return new Message(
                rs.getLong("id"),
                rs.getLong("sender_id"),
                rs.getLong("chat_id"),
                rs.getString("content"),
                rs.getTimestamp("time")
        );
    }

    @Override
    public List<Message> loadAllFrom(long chatId) {
        return jdbcTemplate.query(
                "SELECT * FROM message WHERE chat_id = ? ORDER BY time",
                (rs, rowNum) -> buildMessage(rs),
                chatId
        );
    }

    @Override
    public List<Message> loadAllAfter(Timestamp timestamp, long chatId) {
        if (timestamp == null) {
            return loadAllFrom(chatId);
        }
        return jdbcTemplate.query(
                "SELECT * FROM message WHERE chat_id = ? AND time > ? ORDER BY time",
                (rs, rowNum) -> buildMessage(rs),
                chatId,
                timestamp
        );
    }

    @Override
    public Optional<Message> readById(long id) {
        List<Message> messages = jdbcTemplate.query("SELECT * FROM message WHERE id = ?",
                (rs, rowNum) -> buildMessage(rs), id);
        return Optional.of(messages.get(0));
    }
}
