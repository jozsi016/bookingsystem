package hu.bookingsystem.repository;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ReservationRepositoryTest {

    ReservationRepository repo;

    @Before
    public void setUp() {
        repo = new ReservationRepository();
    }

    @Test
    public void shouldAddReservation() {
        //Given
        Room room = new Room(1L, 5000);
        double price = room.getUnitPrice() * 5;
        Reservation reservation = new Reservation(1L, 1L, room.getId(), LocalDate.now().minusDays(5), LocalDate.now(), price);
        //When
        repo.addReservation(reservation);
        //Then
        assertEquals(1L, repo.getReservations().get(1L).getId());
    }
}
