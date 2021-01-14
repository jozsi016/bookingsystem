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
    public void testCreateRoom() {
        long expectedId = 31;
        roomService.createRoom(31, 4000);
        assertNotNull(roomService.getRoomById(31L));
    }

    @Test
    public void testDeleteRoomById() {
        roomService.deleteRoomById(12L);
        assertNull(roomService.getRoomById(12L));
    }

    @Test
    public void testRoomById() {
        Room room = roomService.getRoomById(12L);
        assertNotNull(room);
    }

    @Test
    public void testGetAllRoom() {
        List<Room> rooms = roomService.getAllRoom();
        int expected = 30;
        assertNotNull(rooms);
        assertEquals(expected, rooms.size());
    }
}
