package by.fmpibsu.stogram.dao.impl;

import by.fmpibsu.stogram.dao.UserDao;
import by.fmpibsu.stogram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> readById(long id) {
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
    public Optional<User> readByUsername(String username) {
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

    @Override
    public User registerUser(String name, String username, String password) {
        var user = new User(name, username, password);

        String sql = "INSERT INTO public.user (name, username, password_hash) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, user.getPasswdHash());
            return ps;
        }, keyHolder);

        long userId = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        user.setId(userId);
        return user;
    }
}
