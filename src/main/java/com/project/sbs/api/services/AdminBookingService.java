package com.project.sbs.api.services;

import com.project.sbs.database.entities.Booking;

import java.util.List;

public interface AdminBookingService {
    List<Booking> getAllPendingBookings();
}
