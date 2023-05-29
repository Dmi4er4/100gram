package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.UserDao;
import by.fmpibsu.stogram.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final List<User> userStorage = new ArrayList<>();

    public UserDaoImpl() {
        userStorage.add(new User(1, "Haccer", "root", "admin")); // HARDCODED
        userStorage.add(new User(2, "Friend", "root2", "admin"));
    }

    @Override
    public Optional<User> getById(long id) {
        for (var user : userStorage) {
            if (user.getId() == id) return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public long idByUname(String username) {
        for (var user : userStorage) {
            if (user.getUsername().equals(username)) return user.getId();
        }
        return -1;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        for (var user : userStorage) {
            if (user.getUsername().equals(username)) return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        userStorage.add(user);
    }

    @Override
    public void delete(User user) {
        userStorage.remove(user);
    }
}
