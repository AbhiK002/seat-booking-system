package com.project.sbs.api.services.user;

import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDashboardService {
    public List<Booking> getBookings(Integer userId) {
        return null;
    }

    public List<Cancellation> getCancellations(Integer userId) {
        return null;
    }

    public Cancellation makeCancellation(Integer userId, Integer bookingId) {
        return null;
    }
}
