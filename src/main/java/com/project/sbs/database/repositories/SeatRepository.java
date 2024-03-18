package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("select f from Seat f where f.seatFloorId= :officeId ")
    List<Seat> getSeatsByFloorId(Floor officeId);
}
