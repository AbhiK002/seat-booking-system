package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Floor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FloorRepository extends CrudRepository<Floor, Integer> {
    @Query("select f from Floor f where f.floor_office_id = :officeId and f.floor_number = :floorNumber")
    List<Floor> getFloorsByFloorNumberAndOfficeId(Integer floorNumber, Integer officeId);
}
