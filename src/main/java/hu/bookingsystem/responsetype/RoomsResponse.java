package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.annotation.JsonCreator;
import hu.bookingsystem.model.Room;

import java.util.List;
import java.util.Objects;

public class RoomsResponse {
    List<Room> rooms;

    @JsonCreator
    public RoomsResponse(List<Room> rooms) {
        this.rooms = rooms;
    }


    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomsResponse that = (RoomsResponse) o;
        return Objects.equals(rooms, that.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms);
    }

    @Override
    public String toString() {
        return "RoomResponse{" +
                "rooms=" + rooms +
                '}';
    }
}



