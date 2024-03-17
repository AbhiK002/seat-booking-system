package com.project.sbs.JpaRepository;

import com.project.sbs.database.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepo extends JpaRepository<Booking,Integer> {

}
