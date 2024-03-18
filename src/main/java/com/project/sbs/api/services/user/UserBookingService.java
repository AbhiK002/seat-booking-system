package com.project.sbs.api.services.user;

import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.repositories.FloorRepository;
import com.project.sbs.database.repositories.OfficeRepository;
import com.project.sbs.database.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookingService {
    private final OfficeRepository officeRepository;
    private final FloorRepository floorRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public UserBookingService(OfficeRepository officeRepository, FloorRepository floorRepository, SeatRepository seatRepository) {
        this.officeRepository = officeRepository;
        this.floorRepository = floorRepository;
        this.seatRepository = seatRepository;
    }
    public List<Office> getAllOffices()
    {
        return (List<Office>) officeRepository.findAll();
    }

    public List<Floor> getAllFloorsWithOfficeId(Integer OfficeId) {
        Office office =officeRepository.findById(OfficeId).orElse(null);
        if(office==null)return null;
        return floorRepository.getFloorsByOfficeId(office);
    }

    public List<Seat> getAllSeatsById(Integer floorId) {
        Floor floor=floorRepository.findById(floorId).orElse(null);
        if(floor==null)return null;
        return seatRepository.getSeatsBySeatFloorId(floor);
    }
}
