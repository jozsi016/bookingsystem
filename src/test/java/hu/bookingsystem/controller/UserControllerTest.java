package hu.bookingsystem.controller;

import hu.bookingsystem.model.User;
import hu.bookingsystem.responsetype.ErrorResponse;
import hu.bookingsystem.responsetype.UserResponse;
import hu.bookingsystem.responsetype.UsersResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnUser() {
        //given
        User expected = new User(1, "Tony");
        this.restTemplate.put("http://localhost:" + port + "/user?userId=1&userName=Tony", Long.class, String.class);
        //when
        ResponseEntity<UserResponse> userResponse = this.restTemplate.getForEntity("http://localhost:" + port + "/user/1", UserResponse.class);
        //then
        User actual = userResponse.getBody().getUser();
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnNotFoundWhenUserIDWrong() {
        //when
        ResponseEntity<ErrorResponse> actual = this.restTemplate.getForEntity("/user/1", ErrorResponse.class);
        //then
        assertThat(actual.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(actual.getBody().getCause(), is("The user is not in the system!"));
    }

    @Test
    public void shouldReturnUsers() {
        //given
        User expected = new User(1, "Tony");
        this.restTemplate.put("http://localhost:" + port + "/user?userId=1&userName=Tony", Long.class, String.class);
        this.restTemplate.put("http://localhost:" + port + "/user?userId=2&userName=Tina", Long.class, String.class);
        //when
        ResponseEntity<UsersResponse> usersResponse = this.restTemplate.getForEntity("http://localhost:" + port + "/users", UsersResponse.class);
        //then
        List<User> users = usersResponse.getBody().getUsers();
        User actual = users.get(0);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnUserNotFoundWhenNoUsersInSystem() {
        //given
        this.restTemplate.delete("http://localhost:" + port + "/user/1");
        this.restTemplate.delete("http://localhost:" + port + "/user/2");
        //when
        ResponseEntity<ErrorResponse> actual = this.restTemplate.getForEntity("/users", ErrorResponse.class);
        //then
        assertThat(actual.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(actual.getBody().getCause(), is("There no any users in the system!"));
    }

    @Test
    public void shouldDeleteUser() {
        //Given
        User expected = new User(2, "Tina");
        User tony = new User(1, "Tony");
        this.restTemplate.put("http://localhost:" + port + "/user?userId=1&userName=Tony", Long.class, String.class);
        this.restTemplate.put("http://localhost:" + port + "/user?userId=2&userName=Tina", Long.class, String.class);
        //when
        this.restTemplate.delete("http://localhost:" + port + "/user/1");
        //then
        ResponseEntity<UsersResponse> usersResponse = this.restTemplate.getForEntity("http://localhost:" + port + "/users", UsersResponse.class);
        List<User> users = usersResponse.getBody().getUsers();
        User actual = users.get(0);
        assertThat(actual, not(tony));
        assertThat(actual, is(expected));
    }
}

