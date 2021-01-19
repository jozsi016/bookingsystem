package hu.bookingsystem.service;

import hu.bookingsystem.model.Room;
import hu.bookingsystem.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RoomServiceTest {

    RoomService roomService;
    RoomRepository roomRepository;

    @Before
    public void setUp() {
        roomRepository = new RoomRepository();
        roomService = new RoomService(roomRepository);
    }

    @Test
    public void shouldCreateRoom() {
        //Given
        long expectedId = 31;
        //When
        roomService.createRoom(31, 4000);
        //Then
        assertNotNull(roomService.getRoomById(31L));
    }

    @Test
    public void shouldDeleteRoomById() {
        //When
        roomService.deleteRoomById(12L);
        //Then
        assertNull(roomService.getRoomById(12L));
    }

    @Test
    public void shouldRoomById() {
        //When
        Room room = roomService.getRoomById(12L);
        //Then
        assertNotNull(room);
    }

    @Test
    public void shouldGetAllRoom() {
        //Given
        int expected = 30;
        //When
        List<Room> rooms = roomService.getAllRoom();
        //Then
        assertEquals(expected, rooms.size());
    }
}
