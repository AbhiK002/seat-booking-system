package com.project.sbs.api.services.user;

import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.config.enums.RequestStatus;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.BookingRepository;
import com.project.sbs.database.repositories.CancellationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDashboardService {
    private BookingRepository bookingRepository;
    private CancellationRepository cancellationRepository;
    private AuthService authService;

    public UserDashboardService(BookingRepository bookingRepository, CancellationRepository cancellationRepository, AuthService authService) {
        this.bookingRepository = bookingRepository;
        this.cancellationRepository = cancellationRepository;
        this.authService = authService;
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
}
