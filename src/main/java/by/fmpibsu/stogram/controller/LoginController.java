package by.fmpibsu.stogram.controller;

import by.fmpibsu.stogram.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

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

        var userOpt = userService.getByUsername(username);
        if (userOpt.isEmpty()) return "{\"error\": \"Username not exists\"}";
        var user = userOpt.get();
        if (!BCrypt.checkpw(password, user.getPasswdHash())) {
            return "{\"error\": \"Incorrect password\"}";
        }

        session.setAttribute("uidSignedIn", user.getId());
        return String.format("{\"id\": %d, \"name\": \"%s\"}", user.getId(), user.getName());
    }
}