package hu.bookingsystem.service;

import hu.bookingsystem.model.Room;
import hu.bookingsystem.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class RoomService {
    private final RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    public void createRoom(long id, double price) {
        Room room = new Room(id, price);
        roomRepo.addRoom(room);
    }

    public void deleteRoomById(Long roomId) {
        roomRepo.getRooms().remove(roomId);
    }

    public Room getRoomById(Long roomId) {
       return roomRepo.getRooms().get(roomId);
    }

    public List<Room> getAllRoom(){
        return roomRepo.getRooms().values().stream().collect(Collectors.toList());
    }
}
