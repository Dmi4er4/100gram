package by.fmpibsu.stogram.controllers;

import by.fmpibsu.stogram.UserDao;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    UserDao userDao = new UserDao();

    public static class LoginForm {

        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping("/api/login")
    @ResponseBody
    public String handleLoginSubmission(@RequestBody LoginForm loginForm, HttpSession session) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        int id = userDao.idByUname(username);
        if (id == -1) {
            return "{\"error\": \"Username not exists\"}";
        }
        var user = userDao.get(id).get();
        if (!BCrypt.checkpw(password, user.getPasswdHash())) {
            return "{\"error\": \"Incorrect password\"}";
        }

        session.setAttribute("uid", id);
        return String.format("{\"id\": %d, \"name\": \"%s\"}", id, user.getName());
    }
}
