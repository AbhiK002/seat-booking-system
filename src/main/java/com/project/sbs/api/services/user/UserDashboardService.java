package com.project.sbs.api.services.user;

import com.project.sbs.api.requests.ModifySwapRequest;
import com.project.sbs.api.responses.SuccessBooleanResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.config.enums.RequestStatus;
import com.project.sbs.database.entities.*;
import com.project.sbs.database.repositories.BookingRepository;
import com.project.sbs.database.repositories.CancellationRepository;
import com.project.sbs.database.repositories.SwapRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDashboardService {
    private BookingRepository bookingRepository;
    private CancellationRepository cancellationRepository;
    private AuthService authService;
    private SwapRequestRepository swapRequestRepository;

    public UserDashboardService(BookingRepository bookingRepository, CancellationRepository cancellationRepository, AuthService authService, SwapRequestRepository swapRequestRepository) {
        this.bookingRepository = bookingRepository;
        this.cancellationRepository = cancellationRepository;
        this.authService = authService;
        this.swapRequestRepository = swapRequestRepository;
    }

    public List<Booking> getBookings(Integer userId) {
        User user = authService.getUserDetails(userId);
        if (user == null) {
            return null;
        }

        return bookingRepository.getBookingsByBookingUserId(user);
    }

    public List<Cancellation> getCancellations(Integer userId) {
        User user = authService.getUserDetails(userId);
        if (user == null) {
            return null;
        }

        return cancellationRepository.getCancellationsByCancellationUserId(user);
    }

    public Cancellation makeCancellation(Integer userId, Integer bookingId) {
        User user = authService.getUserDetails(userId);

        if (user == null) {
            return null;
        }

        return cancellationRepository.save(new Cancellation(0, user, bookingId, RequestStatus.PENDING));
    }

    public List<SwapRequest> findAllSwapRequests(Integer userId) {

        List<SwapRequest> swapRequests= swapRequestRepository.findAll();
        List<SwapRequest> resultRequests=new ArrayList<>();
        for(SwapRequest swapRequest:swapRequests)
        {
            if(swapRequest.getRequestBooking2Id().getBookingUserId().getUserId()==userId)
            {
                resultRequests.add(swapRequest);
            }
        }
        return resultRequests;
    }

    public SuccessBooleanResponse modifySwapRequest(ModifySwapRequest modifySwapRequest) {
        SwapRequest swapRequest=swapRequestRepository.findById(modifySwapRequest.getRequest_id()).orElse(null);
        if(swapRequest==null)return null;
        if(modifySwapRequest.getAccepted())
        {
            Booking booking1=swapRequest.getRequestBooking1Id();
            Booking booking2=swapRequest.getRequestBooking2Id();
            Seat seat= booking1.getBookingSeatId();
            booking1.setBookingSeatId(booking2.getBookingSeatId());
            booking2.setBookingSeatId(seat);
            bookingRepository.save(booking1);
            bookingRepository.save(booking2);
        }
        swapRequestRepository.deleteById(modifySwapRequest.getRequest_id());
        return new SuccessBooleanResponse(true);
    }
}
