package com.project.sbs.api.services.user;

import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.config.enums.RequestStatus;
import com.project.sbs.database.entities.*;
import com.project.sbs.database.repositories.*;
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
    private SwapRequestRepository swapRequestRepository;


    @Autowired
    public BookingPageService(AuthService authService, BookingRepository bookingRepository, CancellationRepository cancellationRepository, FloorRepository floorRepository, SeatRepository seatRepository, SwapRequestRepository swapRequestRepository) {
        this.authService = authService;
        this.bookingRepository = bookingRepository;
        this.cancellationRepository = cancellationRepository;
        this.floorRepository = floorRepository;
        this.seatRepository = seatRepository;
        this.swapRequestRepository = swapRequestRepository;
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

    public SwapRequest swapSeatRequest(Integer userId,Booking booking2)
    {
        User user=authService.getUserDetails(userId);
        Booking booking1=bookingRepository.findBookingByUserId(user);
        return swapRequestRepository.save(new SwapRequest(0,booking1,booking2));
    }
}
