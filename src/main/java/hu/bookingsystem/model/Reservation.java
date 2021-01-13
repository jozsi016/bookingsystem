package hu.bookingsystem.model;

import java.time.LocalDate;

public class Reservation {
    private long id;
    private long userId;
    private long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    public Reservation(long id, long userId, long roomId, LocalDate startDate, LocalDate endDate, double price) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id= " + id +
                ", userId= " + userId +
                ", roomId= " + roomId +
                ", startDate= " + startDate +
                ", endDate= " + endDate +
                ", price= " +price+
                " }";
    }
}
