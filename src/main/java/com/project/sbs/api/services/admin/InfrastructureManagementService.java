package com.project.sbs.api.services.admin;

import com.project.sbs.api.requests.SeatRequest;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.entities.Seat;

public interface InfrastructureManagementService {
    Office createOffice(String OfficeName);

    Floor createFloor(Integer floorId, Integer officeId);

    Seat CreateSeat(SeatRequest seatRequest);

    boolean floorExists(Integer office_id,Integer floor_number);
}
