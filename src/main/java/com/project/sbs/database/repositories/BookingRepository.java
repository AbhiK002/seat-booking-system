package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

}
