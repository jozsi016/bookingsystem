package hu.bookingsystem.service;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.ReservationRepository;
import hu.bookingsystem.repository.RoomRepository;
import hu.bookingsystem.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ReservationServiceTest {
    private ReservationService reservationService;
    private RoomService roomService;
    private RoomRepository roomRepository;
    private UserService userService;
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        roomRepository = new RoomRepository();
        userRepository = new UserRepository();
        roomService = new RoomService(roomRepository);
        userService = new UserService(userRepository);
        reservationRepository = new ReservationRepository();
        reservationService = new ReservationService(roomService, userService, reservationRepository);
    }

    @Test
    public void shouldCreateReservation() {
        //Given
        Room room = new Room(1L, 5000);
        User user = new User(1L, "Tom");
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);
        //When
        long actualRoomId = reservationService.getReservationById(1L).getRoomId();
        //Then
        assertThat(1L, CoreMatchers.is(actualRoomId));
    }

    @Test
    public void shouldGetUserReservationByUserId() {
        //Given
        User user = new User(1L, "Tom");
        Room room = new Room(1L, 5000);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);
        Room room2 = new Room(2L, 5000);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        //When
        long actualRoomId = reservationService.getAllUserReservationsByUserId(1L).get(1).getRoomId();

        //Then
        assertThat(2L, CoreMatchers.is(actualRoomId));
    }

    @Test
    public void shouldFilteredReservationByStartDate() {
        //Given
        User user = new User(1L, "Tom");
        LocalDate forFilter = LocalDate.now().minusYears(1);
        Room room = new Room(1L, 5000);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        Room room2 = new Room(2L, 5000);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        Predicate<Reservation> predicateForAge =
                (r) -> r.getUserId() == user.getId() && r.getStartDate().compareTo(forFilter) >= 0;
        reservationService.createReservation(user, room, start, end);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        //When
        List<Reservation> actual = reservationService.getFilteredReservation(predicateForAge);

        //Then
        assertThat(1L, CoreMatchers.is(actual.get(0).getRoomId()));
    }
}
