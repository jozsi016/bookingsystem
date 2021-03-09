package hu.bookingsystem.controller;

import hu.bookingsystem.localexception.RecourseNotFoundException;
import hu.bookingsystem.model.User;
import hu.bookingsystem.responsetype.ErrorResponse;
import hu.bookingsystem.responsetype.UserResponse;
import hu.bookingsystem.responsetype.UsersResponse;
import hu.bookingsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UsersResponse> getUsers() {
        List<User> allUser = userService.getAllUser();
        UsersResponse usersResponse = new UsersResponse.Builder().withUsers(allUser).build();
        if (allUser.isEmpty()) {
            throw new RecourseNotFoundException("There no any users in the system!");
        } else {
            return ResponseEntity.ok(usersResponse);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        User userById = userService.getUserById(userId);
        UserResponse response = new UserResponse.Builder().withUser(userById).build();
        if (userById == null) {
            throw new RecourseNotFoundException("The user is not in the system!");
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @ExceptionHandler({RecourseNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(RecourseNotFoundException e) {
        ErrorResponse response = new ErrorResponse.Builder().withCause(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
