package by.fmpibsu.stogram.entity;

import java.sql.Timestamp;

public class Message extends Entity {
    private long senderId;
    private long chatId;
    private String content;
    private Timestamp time;

    public Message(long senderId, String content) {
        super(-1);
        this.senderId = senderId;
        this.content = content;
        this.time = new Timestamp(System.currentTimeMillis());
    }
}
