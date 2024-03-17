package com.project.sbs.api.services.admin;

import com.project.sbs.api.services.admin.AdminBookingService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBookingServiceImpl implements AdminBookingService {
    private final BookingRepository bookingRepository;

    public AdminBookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllPendingBookings() {
        return bookingRepository.findAll();
    }
}
