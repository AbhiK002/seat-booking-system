package com.project.sbs.api.services.admin;

import com.project.sbs.api.requests.ModifyBooking;
import com.project.sbs.api.requests.ModifyCancellation;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;

import java.util.List;

public interface AdminBookingService {
    List<Booking> getAllPendingBookings();

    Booking modifyBooking(ModifyBooking modifyBooking);

    List<Cancellation> getAllPendingCancellations();

    Cancellation modifyCancellation(ModifyCancellation modifyCancellation);
}
