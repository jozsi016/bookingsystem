package hu.bookingsystem.controller;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.model.User;
import hu.bookingsystem.responsetype.ReservationResponse;
import hu.bookingsystem.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ReservationService reservationServiceMock;

    @Test
    public void shouldCreateReservation() {
        //given
        User user = new User(1L, "Tom");
        Room room = new Room(1L, 5000);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        Room room2 = new Room(2L, 5000);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationServiceMock.createReservation(user, room, start, end);
        reservationServiceMock.createReservation(user, room2, startFuture, endFuture);

        Reservation expected = new Reservation(
                1, 1, 1, LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20), 25000);
        when(reservationServiceMock.getReservationById(anyLong())).thenReturn(expected);
        //when
        ResponseEntity<ReservationResponse> response =
                this.restTemplate.getForEntity("/reservation/1", ReservationResponse.class);
        //then
        Reservation actual = response.getBody().getReservation();
        verify(reservationServiceMock).getReservationById(anyLong());

    }
}
