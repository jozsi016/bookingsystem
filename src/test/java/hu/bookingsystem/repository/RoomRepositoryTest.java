package hu.bookingsystem.repository;

import hu.bookingsystem.model.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class RoomRepositoryTest {

    RoomRepository roomRepo;
    Map<Long, Room> rooms;

    @Before
    public void setUp() {
        roomRepo = new RoomRepository();
        rooms = roomRepo.getRooms();
    }

    @Test
    public void testInitRooms() {
        int expected = 30;
        assertNotNull(rooms);
        assertEquals(expected, rooms.values().size());
    }

    @Test
    public void testAddRooms() {
        int expected = 31;
        Room room = new Room(31, 5000);
        roomRepo.addRoom(room);
        assertEquals(expected, roomRepo.getRooms().size());
    }
}
