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
        User expectedUser = new User(1, "Tom");
        User kevin = new User(2, "Kevin");
        userRepository.addUser(expectedUser);
        userRepository.addUser(kevin);

        //When
        int actualUserSize = userRepository.getUsers().size();
        User actualUser = userRepository.getUsers().get(1L);

        //Then
        assertEquals(expected, actualUserSize);
        assertEquals(expectedUser, actualUser);
    }
}
