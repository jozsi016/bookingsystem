package hu.bookingsystem.repository;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


import java.time.LocalDate;

public class ReservationRepositoryTest {

    ReservationRepository repo;

    @Before
    public void setUp() {
        repo = new ReservationRepository();
    }

    @Test
    public void testAddReservation(){
        Room room = new Room(1L, 5000);
        double price = room.getUnitPrice() * 5;
        Reservation reservation = new Reservation(1L,1L, room.getId(), LocalDate.now().minusDays(5),LocalDate.now(),price);
        repo.addReservation(reservation);

        assertNotNull(repo.getReservations().get(1L));
        assertEquals(1L, repo.getReservations().get(1L).getId());
    }
}
