package hu.bookingsystem.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.responsetype.RoomResponse;
import hu.bookingsystem.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //direktbe listat adni nem jo otlet
    @GetMapping("/rooms")
    public RoomResponse getRooms() {
        return new RoomResponse(roomService.getAllRoom());
    }

    @GetMapping("/room/{roomId}")
    public RoomResponse getRoom(@PathVariable Long roomId) {
        return new RoomResponse(List.of(roomService.getRoomById(roomId)));
    }

    @PutMapping("/room")
    public void createRoom(@RequestParam Long roomId, double unitPrice) {
        roomService.createRoom(roomId, unitPrice);
    }

    @DeleteMapping("/room/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
    }

}
