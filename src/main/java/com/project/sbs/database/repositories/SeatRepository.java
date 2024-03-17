package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
