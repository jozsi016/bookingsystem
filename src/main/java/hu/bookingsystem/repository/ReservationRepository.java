package hu.bookingsystem.repository;

import hu.bookingsystem.model.Reservation;

import java.util.HashMap;
import java.util.Map;

public class ReservationRepository {
    private final Map<Long, Reservation> reservations = new HashMap<>();

    public Map<Long, Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }
}
