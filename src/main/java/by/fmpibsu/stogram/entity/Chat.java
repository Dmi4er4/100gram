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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chat chat = (Chat) o;

        if (!created.equals(chat.created)) return false;
        return memberIds.equals(chat.memberIds);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + memberIds.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "created=" + created +
                ", memberIds=" + memberIds +
                '}';
    }
}
