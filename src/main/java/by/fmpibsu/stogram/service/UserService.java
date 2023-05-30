package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.UserDao;
import by.fmpibsu.stogram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public Optional<User> getById(long id) {
        return userDao.getById(id);
    }

    public User registerUser(String name, String username, String password) {
        return userDao.registerUser(name, username, password);
    }
}
