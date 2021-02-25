package hu.bookingsystem.controller;

import hu.bookingsystem.localexception.RecourseNotFoundException;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.responsetype.ErrorResponse;
import hu.bookingsystem.responsetype.RoomResponse;
import hu.bookingsystem.responsetype.RoomsResponse;
import hu.bookingsystem.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<RoomsResponse> getRooms() {
        List<Room> allRoom = roomService.getAllRoom();
        RoomsResponse body = new RoomsResponse(allRoom);
        if (allRoom.isEmpty()) {
            throw new RecourseNotFoundException("The room not available!");
        } else {
            return ResponseEntity.ok(body);
        }
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable Long roomId) {
        Room roomById = roomService.getRoomById(roomId);
        RoomResponse response = new RoomResponse(roomById);
        if (roomById == null) {
            throw new RecourseNotFoundException("The room not available!");
        } else {
            return ResponseEntity.ok(response);
        }
    }

    // a jo gyakorlat ha valamit vissza adok, egy id plt 201 created
    @PutMapping("/room")
    public ResponseEntity<RoomResponse> createRoom(@RequestParam Long roomId, double unitPrice) {
        Room room = roomService.createRoom(roomId, unitPrice);
        RoomResponse response = new RoomResponse(room);
        if(room == null) {
            throw new RecourseNotFoundException("The room not created");
        } else {
            return ResponseEntity.ok(response);
        }

    }

    @DeleteMapping("/room/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
    }

    @ExceptionHandler({RecourseNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(RecourseNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
