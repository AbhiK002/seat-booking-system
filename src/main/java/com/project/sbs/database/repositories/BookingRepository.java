package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> getBookingsByBookingUserId(User userId);

    @Query("select b from Booking b where b.bookingStatus = 'PENDING'")
    List<Booking> findAllPendingBookings();

    @Query("select b from Booking b where b.bookingFloorId = :floorId")
    List<Booking> getAllBookingsByFloorId(Floor floorId);

    @Query("select b from Booking b where b.bookingUserId = :userId")
    Booking findBookingByUserId(User userId);

    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.bookingStatus = 'REJECTED' WHERE b.bookingSeatId = :seat AND b.bookingStatus = 'PENDING'")
    void updateByFloorId(Seat seat);
}
