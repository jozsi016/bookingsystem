package hu.bookingsystem.repository;

import hu.bookingsystem.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private final List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRooms(Room room){
        rooms.add(room);
    }
}
