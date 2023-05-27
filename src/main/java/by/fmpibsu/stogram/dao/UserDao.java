package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getById(long id);

    long idByUname(String username);

    Optional<User> getByUsername(String username);

    void save(User user);

    void delete(User user);
}
