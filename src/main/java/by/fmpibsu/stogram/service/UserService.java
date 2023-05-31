package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> readByUsername(String username);

    Optional<User> readById(long id);

    User registerUser(String name, String username, String password);
}
