package by.fmpibsu.stogram.entity;

import org.mindrot.jbcrypt.BCrypt;

public class User extends Entity {
    private String name;
    private String username;
    private String passwdHash;

    public enum FlagPasswordState { RAW, HASHED }

    public User(long id, String name, String username,
                String password, FlagPasswordState state) {
        super(id);
        this.name = name;
        this.username = username;
        this.passwdHash = state == FlagPasswordState.RAW
                ? generateHash(password)
                : password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (!name.equals(user.name)) return false;
        if (!username.equals(user.username)) return false;
        return passwdHash.equals(user.passwdHash);
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + passwdHash.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", passwdHash='" + passwdHash + '\'' +
                '}';
    }
}
