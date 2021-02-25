package hu.bookingsystem.service;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    public long reservationId = 1;
    private RoomService roomService;
    private UserService userService;
    private ReservationRepository reservationRepository;

    public ReservationService(RoomService roomService, UserService userService, ReservationRepository reservationRepository) {
        this.roomService = roomService;
        this.userService = userService;
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(User user, Room room, LocalDate start, LocalDate end) {
        Period startDay = Period.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
        Period endDay = Period.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth());
        int numberOfStay = endDay.getDays() - startDay.getDays();
        double price = room.getUnitPrice() * numberOfStay;
        Reservation reservation = new Reservation(reservationId, user.getId(), room.getId(), start, end, price);
        reservationRepository.addReservation(reservation);
        reservationId++;
    }

    public void deleteReservation(long reservationId) {
        reservationRepository.getReservations().remove(reservationId);
    }

    public List<Room> listOfAvailableRooms(LocalDate start, LocalDate end) {
        List<Room> rooms = roomService.getAllRoom();
        Predicate<Reservation> reservationPredicate =
                r -> (r.getStartDate().compareTo(start) >= 0 && r.getEndDate().compareTo(start) >= 0) ||
                        (r.getStartDate().compareTo(end) >= 0 && r.getEndDate().compareTo(end) >= 0);
        List<Reservation> reservations = reservationRepository.getReservations().values().stream().collect(Collectors.toList());
        for (Reservation r : reservations) {
            if (reservationPredicate.test(r)) {
                Room room = roomService.getRoomById(r.getRoomId());
                rooms.remove(room);
            }
        }
        return rooms;
    }

    public Reservation getReservationById(long reservationId) {
        return reservationRepository.getReservations().get(reservationId);
    }

    public List<Reservation> getAllUserReservationsByUserId(long userId) {
        return reservationRepository.getReservations().values().stream().filter(
                reservation -> userId == reservation.getUserId()).collect(Collectors.toList());
    }

    public List<Reservation> getFilteredReservation(Predicate<Reservation> predicate) {
        return reservationRepository.getReservations().values().
                stream().filter(predicate).collect(Collectors.toList());
    }

    public UserService getUserService() {
        return userService;
    }

    public LocalDate getLocalDate(String date) {
        String delimater = ":";
        String[] parsedDate = date.split(delimater);
        Integer year = Integer.valueOf(parsedDate[0]);
        Integer month = Integer.valueOf(parsedDate[1]);
        Integer dayOfMonth = Integer.valueOf(parsedDate[2]);
        return LocalDate.of(year, month, dayOfMonth);
    }
}

