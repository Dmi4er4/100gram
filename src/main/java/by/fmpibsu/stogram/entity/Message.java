package by.fmpibsu.stogram.entity;

import java.sql.Timestamp;

public class Message {

    private long id;
    private long senderId;
    private long chatId;
    private String content;
    private Timestamp time;

    public Message(long senderId, String content) {
        this.senderId = senderId;
        this.content = content;
        this.time = new Timestamp(System.currentTimeMillis());
    }
}
