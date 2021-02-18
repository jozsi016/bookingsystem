package hu.bookingsystem.controller;

import hu.bookingsystem.model.Room;
import hu.bookingsystem.service.RoomService;
import hu.bookingsystem.responsetype.RoomResponse;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerTestRestTemplateTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private RoomService roomServiceMock;

    @Test
    public void shouldReturnRoom() throws Exception {
        //given
        Room room = new Room(3, 5000);
        //when
        when(roomServiceMock.getRoomById(any())).thenReturn(room);
        //then
        ResponseEntity<RoomResponse> actual = this.testRestTemplate.getForEntity("/room/1", RoomResponse.class);
        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
        assertThat(actual.getBody().getRooms().get(0), is(room));
    }

    @Test
    public void shouldReturnRooms() throws Exception {
        //given
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room(3, 5000);
        Room room2 = new Room(4, 5000);
        rooms.add(room1);
        rooms.add(room2);
        //when
        when(roomServiceMock.getAllRoom()).thenReturn(rooms);
        //then
        ResponseEntity<RoomResponse> actual = this.testRestTemplate.getForEntity("/rooms", RoomResponse.class);
        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
        assertThat(actual.getBody().getRooms().get(0), is(room1));
    }


    @Test
    public void whenStatusMethodNotAllowed() throws Exception {
        //given
        Room room = new Room(3, 5000);
        //when
        when(roomServiceMock.getRoomById(any())).thenReturn(room);
        //then
        ResponseEntity<RoomResponse> actual = this.testRestTemplate.getForEntity("/room", RoomResponse.class);
        assertThat(actual.getStatusCode(), is(HttpStatus.METHOD_NOT_ALLOWED));
    }

    @Test
    public void whenStatusBadRequest() throws Exception {
        //given
        Room room = new Room(3, 5000);
        //when
        when(roomServiceMock.getRoomById(any())).thenReturn(room);
        //then
        ResponseEntity<RoomResponse> actual = this.testRestTemplate.getForEntity("/room/%%20", RoomResponse.class);
        assertThat(actual.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void whenStatusNotFound() throws Exception {
        //given
        Room room = new Room(3, 5000);
        //when
        when(roomServiceMock.getRoomById(any())).thenReturn(room);
        //then
        ResponseEntity<RoomResponse> actual = this.testRestTemplate.getForEntity("/", RoomResponse.class);
        assertThat(actual.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void whenBasicAuth() throws Exception {
        //given
        Room room = new Room(3, 5000);
        //when
        when(roomServiceMock.getRoomById(any())).thenReturn(room);
        //then
        ResponseEntity<RoomResponse> actual = this.testRestTemplate.withBasicAuth("name", "1234").exchange("/room/1", HttpMethod.DELETE, HttpEntity.EMPTY, RoomResponse.class);
        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
    }

}
