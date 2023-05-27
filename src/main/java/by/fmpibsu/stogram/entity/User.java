package by.fmpibsu.stogram.entity;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private final long id;
    private String name;
    private String username;
    private String passwdHash;

    public User(long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.passwdHash = generateHash(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public static String generateHash(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public long getId() {
        return id;
    }
}
