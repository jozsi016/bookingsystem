package hu.bookingsystem.service;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.ReservationRepository;
import hu.bookingsystem.repository.RoomRepository;
import hu.bookingsystem.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testCreateReservation() {
        Room room = new Room(1L, 5000);
        User user = new User(1L, "Tom");
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();

        reservationService.createReservation(user, room, start, end);

        assertNotNull(reservationService.getReservationById(1L));
        assertEquals(1L, reservationService.getReservationById(1L).getRoomId());
    }

    @Test
    public void testGetUserReservationByUserId() {
        User user = new User(1L, "Tom");

        Room room = new Room(1L, 5000);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);

        Room room2 = new Room(2L, 5000);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        assertNotNull(reservationService.getAllUserReservationsByUserId(1L).get(0));
        assertEquals(2, reservationService.getAllUserReservationsByUserId(1L).size());
    }

    @Test
    public void testFilteredReservationByStartDate() {
        User user = new User(1L, "Tom");
        LocalDate forFilter = LocalDate.now().minusYears(1);

        Room room = new Room(1L, 5000);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);

        Room room2 = new Room(2L, 5000);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        Predicate<Reservation> predicateForAge = (r) -> r.getUserId() == user.getId() && r.getStartDate().compareTo(forFilter) >= 0;
        assertEquals(2L, reservationService.getFilteredReservation(predicateForAge).size());
    }

    @Test
    public void testFilterByPeriodOfTime() {
        User user = new User(1L, "Tom");
        LocalDate startFilter = LocalDate.now().minusYears(1);
        LocalDate endFilter = LocalDate.now().plusYears(1);

        Room room = new Room(1L, 5000);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);

        Room room2 = new Room(2L, 5000);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        Predicate<Reservation> predicateForPeriodOfTime =
                (r) -> r.getUserId() == user.getId() && (r.getStartDate().compareTo(startFilter) >= 0
                        && r.getEndDate().compareTo(endFilter) <= 0);

        assertEquals(2L, reservationService.getFilteredReservation(predicateForPeriodOfTime).size());

    }

    @Test
    public void testFilterByPrice() {
        User user = new User(1L, "Tom");
        double priceToFilter = 25000;

        Room room = new Room(1L, 4500);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);

        Room room2 = new Room(2L, 4500);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        Predicate<Reservation> predicateForPrice =
                (r) -> r.getUserId() == user.getId() && r.getPrice() < priceToFilter;

        assertEquals(2L, reservationService.getFilteredReservation(predicateForPrice).size());
    }

    @Test
    public void testListOfAvailableRoom() {
        User user = new User(1L, "Tom");
        LocalDate startFilter = LocalDate.now().minusYears(1);
        LocalDate endFilter = LocalDate.now().plusYears(1);

        Room room = new Room(1L, 4500);
        LocalDate start = LocalDate.now().minusDays(5L);
        LocalDate end = LocalDate.now();
        reservationService.createReservation(user, room, start, end);

        Room room2 = new Room(2L, 4500);
        LocalDate startFuture = LocalDate.now().plusDays(3);
        LocalDate endFuture = LocalDate.now().plusDays(8);
        reservationService.createReservation(user, room2, startFuture, endFuture);

        List<Room> rooms = reservationService.listOfAvailableRoom(startFilter, endFilter);
        assertEquals(28, rooms.size());

        Room room3 = new Room(31L, 4500);
        LocalDate startOldReservation = LocalDate.now().minusYears(3);
        LocalDate endOldReservation = LocalDate.now().minusYears(3).plusDays(7);
        roomRepository.addRoom(room3);
        reservationService.createReservation(user, room3, startOldReservation, endOldReservation);

        rooms = reservationService.listOfAvailableRoom(startFilter, endFilter);
        assertEquals(29, rooms.size());




    }
}
