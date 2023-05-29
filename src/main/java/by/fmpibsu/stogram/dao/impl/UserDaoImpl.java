package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.UserDao;
import by.fmpibsu.stogram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> getById(long id) {
        for (var foundUser : jdbcTemplate.query(
                "SELECT * FROM public.user WHERE id = ?",
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        User.FlagPasswordState.HASHED), id)) {
            return Optional.of(foundUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        for (var foundUser : jdbcTemplate.query(
                "SELECT * FROM public.user WHERE username = ?",
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        User.FlagPasswordState.HASHED), username)) {
            return Optional.of(foundUser);
        }
        return Optional.empty();
    }
}
