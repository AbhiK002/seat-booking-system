package com.project.sbs.api.controllers.user;

import com.project.sbs.api.responses.AnyListResponse;
import com.project.sbs.api.responses.AnyObjectResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.user.UserDashboardService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return null;
    }

    @GetMapping("/cancellations")
    public SimpleResponse getCancellations(
            @RequestHeader("Authorization") String token
    ) {
        return null;
    }

    @PostMapping("/cancellation")
    public SimpleResponse makeCancellation(
            @RequestHeader("Authorization") String token
    ) {
        return null;
    }
}
