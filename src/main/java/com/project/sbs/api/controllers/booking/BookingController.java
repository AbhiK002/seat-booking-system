package com.project.sbs.api.controllers.booking;

import com.project.sbs.api.requests.BookSeatRequest;
import com.project.sbs.api.responses.AnyListResponse;
import com.project.sbs.api.responses.AnyObjectResponse;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.user.BookingPageService;
import com.project.sbs.api.services.user.UserBookingService;
import com.project.sbs.database.entities.*;
import com.project.sbs.database.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookingController {

    private final UserBookingService userBookingService;
    private final BookingPageService bookingPageService;
    private final AuthService authService;

    @Autowired
    public BookingController(UserBookingService userBookingService, BookingPageService bookingPageService, AuthService authService) {
        this.userBookingService = userBookingService;
        this.bookingPageService = bookingPageService;
        this.authService = authService;
    }

    @GetMapping("/offices")
    public ResponseEntity<SimpleResponse> getOffices(
            @RequestHeader("Authorization") String token)
    {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        List<Office> offices = userBookingService.getAllOffices();
        return ResponseEntity.ok(new AnyListResponse<>(offices, true));
    }


    @GetMapping("/floors/{office-id}")
    public ResponseEntity<SimpleResponse> getFloors(
            @RequestHeader("Authorization") String token,
            @PathVariable("office-id") Integer officeId
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        List<Floor> floors = userBookingService.getAllFloorsWithOfficeId(officeId);
        if (floors == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Office does not exist"));
        }

        return ResponseEntity.ok(new AnyListResponse<>(floors, true));
    }

    @GetMapping("/seats/{floor_id}")
    public ResponseEntity<SimpleResponse> getSeats(
            @RequestHeader("Authorization") String token,
            @PathVariable("floor_id") Integer floorId
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        List<Seat> seats = userBookingService.getAllSeatsById(floorId);
        if (seats == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Floor does not exist"));
        } else {
            return ResponseEntity.ok(new AnyListResponse<>(seats, true));
        }
    }

    @PostMapping("/seat/book")
    public ResponseEntity<SimpleResponse> bookSeat(
            @RequestHeader("Authorization") String token,
            @RequestBody BookSeatRequest bookSeatRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        Integer floor_id = bookSeatRequest.getFloor_id();
        Integer seat_id = bookSeatRequest.getSeat_id();
        String start_datetime = bookSeatRequest.getStart_datetime();
        String end_datetime = bookSeatRequest.getEnd_datetime();

        if (floor_id == null || seat_id == null || start_datetime == null || end_datetime == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Some error occurred (null)"));
        }

        if (bookingPageService.isSeatBooked(seat_id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Seat already booked by another user"));
        }

        Booking newBooking = bookingPageService.bookSeat(floor_id, seat_id, userId, start_datetime, end_datetime);

        if (newBooking == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Some error occurred"));
        }

        return ResponseEntity.ok(new AnyObjectResponse<>(newBooking, true));
    }

    @GetMapping("seat/details/{floor_id}")
    public ResponseEntity<SimpleResponse> getSeatDetails(
            @RequestHeader("Authorization") String token,
            @PathVariable("floor_id") Integer floorId
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }
        List<Booking> bookings = bookingPageService.getDetails(floorId);
        if (bookings == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Floor does not exist"));
        }
        return ResponseEntity.ok(new AnyListResponse<>(bookings, true));
    }

    @PostMapping("seat/swap")
    public ResponseEntity<SimpleResponse> createSwapRequest(
            @RequestHeader("Authorization") String token,
            @RequestBody Booking booking
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }
        SwapRequest swapRequest = bookingPageService.swapSeatRequest(userId, booking);
        if (swapRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid input"));
        }
        return ResponseEntity.ok(new AnyObjectResponse<>(swapRequest, true));
    }
}
