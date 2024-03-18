package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> getBookingsByBookingUserId(User userId);

    @Query("select b from Booking b where b.bookingStatus = 'PENDING'")
    List<Booking> findAllPendingBookings();
}
