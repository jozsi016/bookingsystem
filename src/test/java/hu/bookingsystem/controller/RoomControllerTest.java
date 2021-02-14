package hu.bookingsystem.controller;

import hu.bookingsystem.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnRoom(){
        Room expected = new Room(3, 5000);
        Room actual = this.restTemplate.getForObject("http://localhost:" + port + "/room/3", Room.class);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnRooms() throws Exception {
        Room expected = new Room(1, 5000);
        List<LinkedHashMap> rooms = this.restTemplate.getForObject("http://localhost:" + port + "/rooms", List.class);
        long id = (int) rooms.get(0).get("id");
        double unitPrice = (double) rooms.get(0).get("unitPrice");
        Room actual = new Room(id, unitPrice);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldCreateRoom() throws Exception {
        Room expected = new Room(31, 4000);
        this.restTemplate.put("http://localhost:" + port + "/room?roomId=31&unitPrice=4000", Long.class, Double.class);
        List<LinkedHashMap> rooms = this.restTemplate.getForObject("http://localhost:" + port + "/rooms", List.class);
        long id = (int) rooms.get(30).get("id");
        double unitPrice = (double) rooms.get(30).get("unitPrice");
        Room actual = new Room(id, unitPrice);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldDeleteRoomById() throws Exception {
        Room deleted = new Room(1, 5000);
        Room expected = new Room(2, 5000);
        this.restTemplate.delete("http://localhost:" + port + "/room/1");
        List<LinkedHashMap> rooms = this.restTemplate.getForObject("http://localhost:" + port + "/rooms", List.class);
        long id = (int) rooms.get(0).get("id");
        double unitPrice = (double) rooms.get(0).get("unitPrice");
        Room actual = new Room(id, unitPrice);
        assertThat(actual, not(deleted));
        assertThat(actual, is(expected));
    }

}

