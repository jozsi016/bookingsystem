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
        //When
        userService.createUser(1L, "Tom");
        User tom = userService.getUserById(1L);
        //Then
        assertNotNull(tom);
    }

    @Test
    public void shouldDeleteUserBy() {
        //Given
        userService.createUser(1L, "Tom");
        //When
        userService.deleteUserById(1L);
        //Then
        assertNull(userService.getUserById(1L));

    }
}
