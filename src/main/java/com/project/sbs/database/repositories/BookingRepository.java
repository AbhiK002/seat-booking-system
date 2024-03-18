package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> getBookingsByBookingUserId(User userId);
}
