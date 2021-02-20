package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.annotation.JsonCreator;
import hu.bookingsystem.model.Room;

import java.util.List;
import java.util.Objects;

public class RoomResponse {
    Room room;

    @JsonCreator
    public RoomResponse(Room room) {
        this.room = room;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomResponse that = (RoomResponse) o;
        return Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room);
    }

    @Override
    public String toString() {
        return "RoomResponse{" +
                "room=" + room +
                '}';
    }
}



