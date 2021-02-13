package hu.bookingsystem.repository;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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
        Reservation expected = new Reservation(1L, 1L, room.getId(), LocalDate.now().minusDays(5), LocalDate.now(), price);
        ReservationRepository repoSpy = spy(repo);
        //When
        repoSpy.addReservation(expected);
        Reservation actual = repoSpy.getReservations().get(1L);
        //Then
      //  assertEquals(expected, actual);
        assertThat(actual, is(expected));
        verify(repoSpy).addReservation(expected);
    }
}
