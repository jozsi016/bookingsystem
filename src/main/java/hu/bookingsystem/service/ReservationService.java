package hu.bookingsystem.service;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 *   *reservation createReservation(User, Room, start, end)
 *   *getReservation(reservationId)
 *   *getAllUserReservationBy(userId);
 *
 *
 */
public class ReservationService {
    public static long reservationId = 1;
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
        int numberOfStay = startDay.getDays() - endDay.getDays();
        double price = room.getUnitPrice() * numberOfStay;
        Reservation reservation = new Reservation(reservationId, user.getId(), room.getId(), start, end, price);
        reservationRepository.addReservation(reservation);
    }

    public Reservation getReservationById(long reservationId) {
        return reservationRepository.getReservations().get(reservationId);
    }

    public List<Reservation> getAllUserReservationsByUserId(long userId) {
        return reservationRepository.getReservations().values().stream().filter(
                reservation -> userId == reservation.getUserId()).collect(Collectors.toList());
    }

    /*
     *filterbyStartDate(userid, date)
     *filterbyperiod(userid, periodPredicate)
     *filterbyPrice(userId,pricePredicate)
     *
     *filter(1, r -> r.price >1000)
     *     Predicate<Employee> predicateForAge = (e) -> e.age >= 25;
      Predicate<Employee> predicateForName = (e) -> e.name.startsWith("A");
      for(Employee emp : empList) {
         if(predicateForAge.test(emp)) {
            System.out.println(emp.name +" is eligible by age");
         }
      }
     */
    public List<Reservation> getFilteredReservation(Predicate predicate) {
        return (List<Reservation>) reservationRepository.getReservations().values().
                stream().filter(predicate).collect(Collectors.toList());
    }
}

