package hu.bookingsystem.repository;

import hu.bookingsystem.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRepositoryTest {

    UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testAddUsers() {
        int expected = 2;

        User Tom = new User(1, "Tom");
        User Kevin = new User(2, "Kevin");
        userRepository.addUser(Tom);
        userRepository.addUser(Kevin);

        assertNotNull(userRepository.getUsers());
        assertEquals(expected, userRepository.getUsers().size());
    }
}
