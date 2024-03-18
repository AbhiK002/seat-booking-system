package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.responses.AnyListResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.admin.AdminBookingService;
import com.project.sbs.api.responses.BookingsListResponse;
import com.project.sbs.database.entities.Booking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminDashboard {
    private final AdminBookingService adminBookingService;

    public AdminDashboard(AdminBookingService adminBookingService) {
        this.adminBookingService = adminBookingService;
    }

    @GetMapping("/bookings")
    public SimpleResponse getbookings(@RequestHeader("Authorization") String token)
    {
        List<Booking> bookings = adminBookingService.getAllPendingBookings();
        return new AnyListResponse<Booking>(bookings, true);
    }

}