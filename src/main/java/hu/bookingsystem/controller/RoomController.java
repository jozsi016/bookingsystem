package hu.bookingsystem.controller;

import hu.bookingsystem.model.Room;
import hu.bookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    List<Room> getRooms() {
        return roomService.getAllRoom();
    }

    //parameter objectum, olvashatos, indempotent implementacio
    // bement szures ! fontos
    // beepitet validacio
    @GetMapping("/room/{roomId}")
    Room getRoom(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @PutMapping("/room")
    void createRoom(@RequestParam Long roomId, double unitPrice) {
        roomService.createRoom(roomId, unitPrice);
    }

    @DeleteMapping("/room")
    void deleteRoom(@RequestParam Long roomId) {
        roomService.deleteRoomById(roomId);
    }

}
