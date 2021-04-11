package hu.bookingsystem.controller;

import hu.bookingsystem.model.Reservation;
import hu.bookingsystem.model.Room;
import hu.bookingsystem.model.User;
import hu.bookingsystem.requestobjcet.CreateReservationRequest;
import hu.bookingsystem.responsetype.ReservationResponse;
import hu.bookingsystem.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public void createReservation(CreateReservationRequest createReservationRequest) {
        LocalDate start = reservationService.getLocalDate(createReservationRequest.getStartStr());
        LocalDate end = reservationService.getLocalDate(createReservationRequest.getEndStr());
        Optional<User> user = Optional.of(reservationService.getUserService().getUserById(createReservationRequest.getUserId()));
        List<Room> rooms = reservationService.listOfAvailableRooms(start, end);
        Optional<Room> room = rooms.stream().filter(r -> createReservationRequest.getRoomId() == r.getId()).findFirst();
        if (room.isPresent() && user.isPresent()) {
            reservationService.createReservation(user.get(), room.get(), start, end);
        }
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable long reservationId) {
        Reservation reservationById = reservationService.getReservationById(reservationId);
        ReservationResponse response = new ReservationResponse.Builder().withReservation(reservationById).build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/reservation/availablerooms")
    public List<Room> listOfAvailableRooms(@RequestParam String startStr, String endStr) {
        LocalDate start = reservationService.getLocalDate(startStr);
        LocalDate end = reservationService.getLocalDate(endStr);
        return reservationService.listOfAvailableRooms(start, end);
    }

    @DeleteMapping("/reservation/{reservationId}")
    public void deleteReservation(@PathVariable long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @GetMapping("/reservation/user/{userId}")
    public List<Reservation> getAllUserReservationsByUserId(@RequestParam long userId) {
        return reservationService.getAllUserReservationsByUserId(userId);
    }

    public List<Reservation> getFilteredReservation(Predicate<Reservation> predicate) {
        //  Predicate<Reservation> predicateForAge =
        //                (r) -> r.getUserId() == user.getId() && r.getStartDate().compareTo(forFilter) >= 0;
        return reservationService.getFilteredReservation(predicate);
    }

}
