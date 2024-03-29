package by.fmpibsu.stogram.controller;

import by.fmpibsu.stogram.entity.User;
import by.fmpibsu.stogram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user-by-id")
    @ResponseBody
    ResponseEntity<User> getUser(@RequestBody long id) {
        var result = userService.readById(id);
        return result
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @PostMapping("/api/user-by-uname")
    @ResponseBody
    ResponseEntity<User> getUser(@RequestBody String username) {
        var result = userService.readByUsername(username);
        return result
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }
}
