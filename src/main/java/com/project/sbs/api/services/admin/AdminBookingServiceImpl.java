package com.project.sbs.api.services.admin;

import com.project.sbs.api.requests.ModifyBooking;
import com.project.sbs.config.enums.RequestStatus;
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
        return bookingRepository.findAllPendingBookings();
    }

    @Override
    public Booking modifyBooking(ModifyBooking modifyBooking) {
        Booking booking =bookingRepository.findById(modifyBooking.getBooking_id()).orElse(null);
        if(booking==null)return null;
        if(modifyBooking.getAccepted())
        {
            booking.setBookingStatus(RequestStatus.ACCEPTED);
        }
        else
        {
            booking.setBookingStatus(RequestStatus.REJECTED);
        }
        return bookingRepository.save(booking);
    }
}
