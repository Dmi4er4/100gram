package by.fmpibsu.stogram.service.impl;

import by.fmpibsu.stogram.dao.UserDao;
import by.fmpibsu.stogram.entity.User;
import by.fmpibsu.stogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> readByUsername(String username) {
        return userDao.readByUsername(username);
    }

    @Override
    public Optional<User> readById(long id) {
        return userDao.readById(id);
    }

    @Override
    public User registerUser(String name, String username, String password) {
        return userDao.registerUser(name, username, password);
    }
}
