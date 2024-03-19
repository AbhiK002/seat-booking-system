package com.project.sbs.api.services.admin;

import com.project.sbs.api.requests.ModifyBooking;
import com.project.sbs.api.requests.ModifyCancellation;
import com.project.sbs.config.enums.RequestStatus;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.repositories.BookingRepository;
import com.project.sbs.database.repositories.CancellationRepository;
import com.project.sbs.database.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBookingServiceImpl implements AdminBookingService {
    private final BookingRepository bookingRepository;
    private final CancellationRepository cancellationRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public AdminBookingServiceImpl(BookingRepository bookingRepository, CancellationRepository cancellationRepository, SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.cancellationRepository = cancellationRepository;
        this.seatRepository = seatRepository;
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
            Seat seat=booking.getBookingSeatId();
            seat.setSeatBooked(true);
            seatRepository.save(seat);
        }
        else
        {
            booking.setBookingStatus(RequestStatus.REJECTED);
        }
        return bookingRepository.save(booking);
    }

    @Override
    public List<Cancellation> getAllPendingCancellations() {
        return cancellationRepository.findAllPendingCancellations();
    }

    @Override
    public Cancellation modifyCancellation(ModifyCancellation modifyCancellation) {
        Cancellation cancellation =cancellationRepository.findById(modifyCancellation.getCancellation_id()).orElse(null);
        if(cancellation==null)return null;
        if(modifyCancellation.getAccepted())
        {
            cancellation.setCancellationStatus(RequestStatus.ACCEPTED);
        }
        else
        {
            cancellation.setCancellationStatus(RequestStatus.REJECTED);
        }
        return cancellationRepository.save(cancellation);
    }
}
