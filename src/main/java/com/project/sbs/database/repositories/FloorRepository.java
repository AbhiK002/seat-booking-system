package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Integer> {
    @Query("select f from Floor f where f.floorOfficeId = :officeId and f.floorNumber = :floorNumber")
    List<Floor> getFloorsByFloorNumberAndOfficeId(Integer floorNumber, Office officeId);

    @Query("select f from Floor f where f.floorOfficeId= :officeId ")
    List<Floor> getFloorsByOfficeId(Office officeId);
}
