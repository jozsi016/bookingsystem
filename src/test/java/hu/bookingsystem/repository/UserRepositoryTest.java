package hu.bookingsystem.repository;

import hu.bookingsystem.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {

    UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void shouldAddUsers() {
        //Given
        int expected = 2;
        User tom = new User(1, "Tom");
        User kevin = new User(2, "Kevin");

        //When
        userRepository.addUser(tom);
        userRepository.addUser(kevin);

        //Then
        assertEquals(expected, userRepository.getUsers().size());
        assertEquals(tom, userRepository.getUsers().get(1L));
    }
}
