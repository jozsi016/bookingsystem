package hu.bookingsystem.repository;

import hu.bookingsystem.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private final List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
