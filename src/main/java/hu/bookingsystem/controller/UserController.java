package hu.bookingsystem.controller;

import hu.bookingsystem.model.User;
import hu.bookingsystem.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user")
    public void createUser(@RequestParam Long userId, String userName) {
        userService.createUser(userId, userName);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping("user/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
