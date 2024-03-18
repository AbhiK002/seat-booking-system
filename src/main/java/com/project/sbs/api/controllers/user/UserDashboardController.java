package com.project.sbs.api.controllers.user;

import com.project.sbs.api.requests.CancellationRequest;
import com.project.sbs.api.responses.AnyListResponse;
import com.project.sbs.api.responses.AnyObjectResponse;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.user.UserDashboardService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserDashboardController {
    private AuthService authService;
    private UserDashboardService userDashboardService;

    @Autowired
    public UserDashboardController(AuthService authService, UserDashboardService userDashboardService) {
        this.authService = authService;
        this.userDashboardService = userDashboardService;
    }

    @GetMapping("/bookings")
    public SimpleResponse getBookings(
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        List<Booking> bookingsList = userDashboardService.getBookings(userId);

        if (bookingsList == null) {
            return new ErrorResponse("Some error occurred (null)");
        }

        return new AnyListResponse<Booking>(
                bookingsList,
                true
        );
    }

    @GetMapping("/cancellations")
    public SimpleResponse getCancellations(
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        List<Cancellation> cancellationsList = userDashboardService.getCancellations(userId);

        if (cancellationsList == null) {
            return new ErrorResponse("Some error occurred (null)");
        }

        return new AnyListResponse<Cancellation>(
                cancellationsList,
                true
        );
    }

    @PostMapping("/cancellation")
    public SimpleResponse makeCancellation(
            @RequestHeader("Authorization") String token,
            @RequestBody CancellationRequest cancellationRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        Integer booking_id = cancellationRequest.getBooking_id();
        if (booking_id == null) {
            return new ErrorResponse("Some error occurred (null)");
        }

        Cancellation newCancellation = userDashboardService.makeCancellation(userId, booking_id);

        if (newCancellation == null) {
            return new ErrorResponse("Some error occurred (refresh)");
        }

        return new AnyObjectResponse<Cancellation>(
                newCancellation,
                true
        );
    }
}
