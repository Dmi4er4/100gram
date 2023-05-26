package by.fmpibsu.stogram;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String name;
    private String username;
    private String passwdHash;

    public User(String name, String username, String passwdHash) {
        this.name = name;
        this.username = username;
        this.passwdHash = passwdHash;
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
}
