package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.requests.ModifyBooking;
import com.project.sbs.api.requests.ModifyCancellation;
import com.project.sbs.api.responses.*;
import com.project.sbs.api.services.admin.AdminBookingService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdminDashboardController {
    private final AdminBookingService adminBookingService;

    public AdminDashboardController(AdminBookingService adminBookingService) {
        this.adminBookingService = adminBookingService;
    }

    @GetMapping("/bookings")
    public SimpleResponse getbookings(@RequestHeader("Authorization") String token)
    {
        List<Booking> bookings = adminBookingService.getAllPendingBookings();

        return new AnyListResponse<Booking>(bookings, true);
    }

    @PatchMapping("/modify-booking")
    public SimpleResponse modifyBooking(
            @RequestHeader("Authorization") String token,
            @RequestBody ModifyBooking modifyBooking
            )
    {
        Booking booking=adminBookingService.modifyBooking(modifyBooking);
        if(booking ==null)return new ErrorResponse("Booking Id invalid");
        return new AnyObjectResponse<Booking>(booking,true);
    }

    @GetMapping("/cancellations")
    public SimpleResponse getCancellations(@RequestHeader("Authorization") String token)
    {
        List<Cancellation> cancellations = adminBookingService.getAllPendingCancellations();

        return new AnyListResponse<Cancellation>(cancellations, true);
    }

    @PatchMapping("/modify-cancellation")
    public SimpleResponse modifyCancellation(
            @RequestHeader("Authorization") String token,
            @RequestBody ModifyCancellation modifyCancellation
    )
    {
        Cancellation cancellation=adminBookingService.modifyCancellation(modifyCancellation);
        if(cancellation ==null)return new ErrorResponse("Cancellation Id invalid");
        return new AnyObjectResponse<Cancellation>(cancellation,true);
    }
}