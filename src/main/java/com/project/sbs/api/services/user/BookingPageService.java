package com.project.sbs.api.services.user;

import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.config.enums.RequestStatus;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.BookingRepository;
import com.project.sbs.database.repositories.CancellationRepository;
import com.project.sbs.database.repositories.FloorRepository;
import com.project.sbs.database.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingPageService {
    private AuthService authService;
    private BookingRepository bookingRepository;
    private CancellationRepository cancellationRepository;
    private FloorRepository floorRepository;
    private SeatRepository seatRepository;

    @Autowired
    public BookingPageService(AuthService authService, BookingRepository bookingRepository, CancellationRepository cancellationRepository, FloorRepository floorRepository, SeatRepository seatRepository) {
        this.authService = authService;
        this.bookingRepository = bookingRepository;
        this.cancellationRepository = cancellationRepository;
        this.floorRepository = floorRepository;
        this.seatRepository = seatRepository;
    }

    public  List<Booking> getDetails(Integer floorId) {
         Floor floor=floorRepository.findById(floorId).orElse(null);
         if(floor==null)return null;
         return bookingRepository.getAllBookingsByFloorId(floor);
    }

    public Booking bookSeat(Integer floor_id, Integer seat_id, Integer user_id, String start_datetime, String end_datetime) {
        User user = authService.getUserDetails(user_id);
        Floor floor = floorRepository.findById(floor_id).orElse(null);
        Seat seat = seatRepository.findById(seat_id).orElse(null);

        if (user == null || floor == null || seat == null) {
            return null;
        }

        return bookingRepository.save(new Booking(
                0,
                authService.getUserDetails(user_id),
                floor,
                seat,
                start_datetime,
                end_datetime,
                RequestStatus.PENDING
        ));
    }
}
