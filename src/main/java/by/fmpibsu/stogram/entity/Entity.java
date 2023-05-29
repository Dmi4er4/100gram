package by.fmpibsu.stogram.entity;

public abstract class Entity {
    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
}
