package hu.bookingsystem.service;

import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserServiceTest {
    UserRepository userRepository;
    UserService userService;

    @Before
    public void setUp() {
        this.userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
    }

    @Test
    public void shouldCreateUser() {
        //Given
        userService.createUser(1L, "Tom");
        //When
        User actualUser = userService.getUserById(1L);
        //Then
        assertNotNull(actualUser);
    }

    @Test
    public void shouldDeleteUserBy() {
        //Given
        userService.createUser(1L, "Tom");
        userService.deleteUserById(1L);
        //When
        User actualUser = userService.getUserById(1L);
        //Then
        assertNull(actualUser);
    }
}
