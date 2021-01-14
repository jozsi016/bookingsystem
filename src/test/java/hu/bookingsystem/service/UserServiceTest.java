package hu.bookingsystem.service;

import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserRepository userRepository;
    UserService userService;

    @Before
    public void setUp(){
        this.userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
    }

    @Test
    public void testCreateUser(){
        userService.createUser(1L, "Tom");
        User tom = userService.getUserById(1L);
        assertNotNull(tom);
    }

    @Test
    public void testDeleteUserBy(){
        userService.createUser(1L, "Tom");
        userService.deleteUserById(1L);
        assertNull(userService.getUserById(1L));
    }
}
