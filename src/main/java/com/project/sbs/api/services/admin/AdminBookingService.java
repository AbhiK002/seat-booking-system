package com.project.sbs.api.services.admin;

import com.project.sbs.api.requests.ModifyBooking;
import com.project.sbs.database.entities.Booking;

import java.util.List;

public interface AdminBookingService {
    List<Booking> getAllPendingBookings();

    Booking modifyBooking(ModifyBooking modifyBooking);
}
