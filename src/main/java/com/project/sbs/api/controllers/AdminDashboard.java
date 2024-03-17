package com.project.sbs.api.controllers;

import com.project.sbs.database.repositories.BookingRepository;
import com.project.sbs.api.responses.BookingResponse;
import com.project.sbs.database.entities.Booking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminDashboard {

//    @GetMapping("/bookings")
//    public BookingResponse getbookings(@RequestHeader("Authorization") String token)
//    {
//        int the_id=1;
//        List<Booking> data= BookingRepository.findById(the_id);
//        BookingResponse response=new BookingResponse();
//        response.setData(data);
//        response.setSuccessField(true);
//        return response;
//    }

}