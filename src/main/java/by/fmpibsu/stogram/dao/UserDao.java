package by.fmpibsu.stogram.dao;

import by.fmpibsu.stogram.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> readByUsername(String username);

    User registerUser(String name, String username, String password);
}
