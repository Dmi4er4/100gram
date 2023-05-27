package by.fmpibsu.stogram.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Chat {
    private long id;
    private Date created;
    private List<Long> memberIds;

    public Chat(long id) {
        this.id = id;
        this.created = Date.valueOf(LocalDate.now());
    }

    public long getId() {
        return id;
    }

    public Date getDateCreated() {
        return created;
    }

    public List<Long> getMemberIds() {
        return memberIds;
    }

    void addMember(long id) {
        memberIds.add(id);
    }
}
