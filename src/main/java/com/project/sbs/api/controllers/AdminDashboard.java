package com.project.sbs.api.controllers;

import com.project.sbs.api.responses.SampleResponse;
import com.project.sbs.database.entities.Booking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminDashboard {
    @GetMapping("/bookings")
    public SampleResponse getbookings(@RequestHeader("Authorization") String token)
    {
        return new SampleResponse(null, true);
    }
}
