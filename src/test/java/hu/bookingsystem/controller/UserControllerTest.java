package hu.bookingsystem.controller;

import hu.bookingsystem.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.LinkedHashMap;
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
        User expected = new User(1, "Tony");
        this.restTemplate.put("http://localhost:" + port + "/user?userId=1&userName=Tony", Long.class, String.class);
        User actual = this.restTemplate.getForObject("http://localhost:" + port + "/user/1", User.class);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnUsers(){
        User expected = new User(1, "Tony");
        this.restTemplate.put("http://localhost:" + port + "/user?userId=1&userName=Tony", Long.class, String.class);
        this.restTemplate.put("http://localhost:" + port + "/user?userId=2&userName=Tina", Long.class, String.class);
        List<LinkedHashMap> users = this.restTemplate.getForObject("http://localhost:" + port + "/users", List.class);
        long id = (int) users.get(0).get("id");
        String unitPrice = (String) users.get(0).get("name");
        User actual = new User(id, unitPrice);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldDeleteUser(){
        User expected = new User(2, "Tina");
        User tony = new User(1, "Tony");
        this.restTemplate.put("http://localhost:" + port + "/user?userId=1&userName=Tony", Long.class, String.class);
        this.restTemplate.put("http://localhost:" + port + "/user?userId=2&userName=Tina", Long.class, String.class);
        this.restTemplate.delete("http://localhost:" + port + "/user/1");
        List<LinkedHashMap> users = this.restTemplate.getForObject("http://localhost:" + port + "/users", List.class);
        long id = (int) users.get(0).get("id");
        String unitPrice = (String) users.get(0).get("name");
        User actual = new User(id, unitPrice);
        assertThat(actual, not(tony));
        assertThat(actual, is(expected));
    }

}

