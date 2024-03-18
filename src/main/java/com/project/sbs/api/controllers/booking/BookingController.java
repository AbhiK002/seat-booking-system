package com.project.sbs.api.controllers.booking;

import com.project.sbs.api.requests.BookSeatRequest;
import com.project.sbs.api.responses.AnyListResponse;
import com.project.sbs.api.responses.AnyObjectResponse;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.user.BookingPageService;
import com.project.sbs.api.services.user.UserBookingService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SimpleResponse getOffices(
            @RequestHeader("Authorization") String token)
    {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        List<Office> offices= userBookingService.getAllOffices();
        return new AnyListResponse<Office>(offices,true);
    }

    @GetMapping("/floors/{office-id}")
    public SimpleResponse getFloors(
            @RequestHeader("Authorization") String token,
            @PathVariable("office-id") Integer officeId
            )
    {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        List<Floor> floors= userBookingService.getAllFloorsWithOfficeId(officeId);
        if(floors==null)return new ErrorResponse("office does not exists");
        return new AnyListResponse<Floor>(floors,true);
    }
//
    @GetMapping("/seats/{floor_id}")
    public SimpleResponse getSeats(
            @RequestHeader("Authorization") String token,
            @PathVariable("floor_id") Integer floorId
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        List<Seat> seats = userBookingService.getAllSeatsById(floorId);
        if (seats == null) {
            return new ErrorResponse("Floor does not exist");
        } else {
            System.out.println(seats);
            return new AnyListResponse<Seat>(seats, true);
        }
    }

    @PostMapping("/seat/book")
    public SimpleResponse bookSeat(
            @RequestHeader("Authorization") String token,
            @RequestBody BookSeatRequest bookSeatRequest
            ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        Integer floor_id = bookSeatRequest.getFloor_id();
        Integer seat_id = bookSeatRequest.getSeat_id();
        String start_datetime = bookSeatRequest.getStart_datetime();
        String end_datetime = bookSeatRequest.getEnd_datetime();

        if (floor_id == null || seat_id == null || start_datetime == null || end_datetime == null) {
            return new ErrorResponse("Some error occurred (null)");
        }

        Booking newBooking = bookingPageService.bookSeat(floor_id, seat_id, userId, start_datetime, end_datetime);

        if (newBooking == null) {
            return new ErrorResponse("Some error occurred");
        }

        return new AnyObjectResponse<Booking>(
                newBooking,
                true
        );
    }

    @GetMapping("seat/details/{floor_id}")
    public SimpleResponse getSeatDetails(
            @RequestHeader("Authorization") String token,
            @PathVariable("floor_id") Integer floorId
    )
    {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }
        List<Booking> bookings=bookingPageService.getDetails(floorId);
        if(bookings==null)return new ErrorResponse("Floor does not exist ");
        return new AnyListResponse<Booking>(bookings,true);
    }
}
