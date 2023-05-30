package by.fmpibsu.stogram.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Chat extends Entity {
    public Chat(long id) {
        super(id);
    }

    public Chat(List<Long> memberIds) {
        super(-1);
        this.memberIds = memberIds;
    }

    public Chat(long id, Date created) {
        super(id);
        this.created = created;
    }

    private Date created;
    private List<Long> memberIds = new ArrayList<>();
    public Date getDateCreated() {
        return created;
    }

    public List<Long> getMemberIds() {
        return memberIds;
    }

    public void addMember(long id) {
        memberIds.add(id);
    }

    public void setMemberIds(List<Long> memberIds) {
        this.memberIds = memberIds;
    }

    public void setDateCreated(Date created) {
        this.created = created;
    }
}
