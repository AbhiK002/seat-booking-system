package com.project.sbs.api.controllers;

import com.project.sbs.database.entities.Booking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminDashboard {
    @GetMapping("/bookings")
    public List<Booking> getbookings(@RequestHeader("Authorization") String token)
    {
        List<Booking> pendingBookings = null;
        return pendingBookings;
    }
}
