package hu.bookingsystem.model;

public class Room {
    private long id;
    private double unitPrice;

    public Room(long id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Room{ " + "id= " + id + ", unitPrice= " + unitPrice + " }";
    }
}
