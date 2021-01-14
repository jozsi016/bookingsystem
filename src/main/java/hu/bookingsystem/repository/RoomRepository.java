package hu.bookingsystem.repository;

import hu.bookingsystem.model.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomRepository {
    private final Map<Long, Room> rooms = initRooms();

    public Map<Long, Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    public Map<Long, Room> initRooms() {
        Map<Long, Room> rooms = new HashMap<>();
        for (long i = 1; i <= 30; i++) {
            rooms.put(i, new Room(i, 5000));
        }
        return rooms;
    }
}
