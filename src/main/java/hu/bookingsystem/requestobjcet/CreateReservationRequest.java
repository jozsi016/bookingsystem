package hu.bookingsystem.requestobjcet;

import org.springframework.lang.NonNull;

public class CreateReservationRequest {
    private final long userId;
    private final long roomId;
    private final String startStr;
    private final String endStr;

    public CreateReservationRequest(long userId, long roomId, String startStr, String endStr) {
        this.userId = userId;
        this.roomId = roomId;
        this.startStr = startStr;
        this.endStr = endStr;
    }

    public long getUserId() {
        return userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getStartStr() {
        return startStr;
    }

    public String getEndStr() {
        return endStr;
    }
}
