package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> getById(long id);

    long idByUname(String username);

    Optional<User> getByUsername(String username);

    void save(User user);

    void delete(User user);
}
