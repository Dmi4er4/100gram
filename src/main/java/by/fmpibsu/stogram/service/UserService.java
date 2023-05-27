package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.impl.UserDaoImpl;
import by.fmpibsu.stogram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserDaoImpl userDao;

    @Autowired
    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
