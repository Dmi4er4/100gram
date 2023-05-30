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
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public static class LoginForm {

        public String username;
        public String password;
    }

    private static class UserJson {
        public String error;
        public long id;
        public String name;
    }

    @PostMapping("/api/login")
    @ResponseBody
    public UserJson handleLoginSubmission(@RequestBody LoginForm loginForm, HttpSession session) {
        var userJson = new UserJson();

        var userOpt = userService.getByUsername(loginForm.username);
        if (userOpt.isEmpty()) {
            userJson.error = "Username not exists";
            return userJson;
        }

        var user = userOpt.get();
        if (!BCrypt.checkpw(loginForm.password, user.getPasswdHash())) {
            userJson.error = "Incorrect password";
            return userJson;
        }

        session.setAttribute("uidSignedIn", user.getId());

        userJson.id = user.getId();
        userJson.name = user.getName();
        return userJson;
    }

    public static class RegisterForm {
        public String name;
        public String username;
        public String password;
    }

    @PostMapping("/api/register")
    @ResponseBody
    public UserJson handleRegisterSubmission(@RequestBody RegisterForm registerForm, HttpSession session) {
        var userJson = new UserJson();

        if (userService.getByUsername(registerForm.username).isPresent()) {
            userJson.error = "Username already exists";
            return userJson;
        }

        var user = userService.registerUser(
                registerForm.name,
                registerForm.username,
                registerForm.password
        );

        session.setAttribute("uidSignedIn", user.getId());

        userJson.id = user.getId();
        userJson.name = user.getName();
        return userJson;
    }
}
