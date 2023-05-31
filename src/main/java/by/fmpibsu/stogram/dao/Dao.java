package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.entity.Entity;

import java.util.Optional;

public interface Dao<T extends Entity> {

    Optional<T> readById(long id);
}
