package by.fmpibsu.stogram.entity;

import java.sql.Timestamp;

public class Message extends Entity {
    private long senderId;
    private long chatId;
    private final String content;
    private final Timestamp time;

    public Message(long senderId, String content) {
        super(-1);
        this.senderId = senderId;
        this.content = content;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Message(long id, long senderId, long chatId, String content, Timestamp time) {
        super(id);
        this.senderId = senderId;
        this.chatId = chatId;
        this.content = content;
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }
}
