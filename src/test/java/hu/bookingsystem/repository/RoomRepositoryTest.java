package hu.bookingsystem.repository;

import hu.bookingsystem.model.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RoomRepositoryTest {

    RoomRepository roomRepo;
    Map<Long, Room> rooms;

    @Before
    public void setUp() {
        roomRepo = new RoomRepository();
        rooms = roomRepo.getRooms();
    }

    @Test
    public void shouldInitRooms() {
        //Given
        int expected = 30;
        //Then
        assertEquals(expected, rooms.values().size());
    }

    @Test
    public void shouldAddRooms() {
        //given
        int expected = 31;
        Room room = new Room(31, 5000);
        //when
        roomRepo.addRoom(room);
        //then
        assertEquals(expected, roomRepo.getRooms().size());
    }
}
