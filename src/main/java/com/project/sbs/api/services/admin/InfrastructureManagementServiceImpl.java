package com.project.sbs.api.services.admin;


import com.project.sbs.api.requests.SeatRequest;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.services.admin.InfrastructureManagementService;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.repositories.FloorRepository;
import com.project.sbs.database.repositories.OfficeRepository;
import com.project.sbs.database.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureManagementServiceImpl implements InfrastructureManagementService {


    private final OfficeRepository officeRepository;
    private final FloorRepository floorRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public InfrastructureManagementServiceImpl(OfficeRepository officeRepository, FloorRepository floorRepository, SeatRepository seatRepository) {
        this.officeRepository = officeRepository;
        this.floorRepository = floorRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public Office createOffice(String OfficeName) {
        return officeRepository.save(new Office(0, OfficeName));
    }

    @Override
    public Floor createFloor(Integer floorNumber, Integer officeId) {

        if(floorExists(officeId,floorNumber))
        {
            return null;
        }
        Office office=officeRepository.findById(officeId).orElse(null);
        if(office==null)return null;
        return floorRepository.save(new Floor(0,floorNumber,office));
    }

    @Override
    public Seat CreateSeat (SeatRequest seatRequest)
    {

        Floor floor=floorRepository.findById(seatRequest.getFloor_id()).orElse(null);
        if(floor==null)return null;
        return  seatRepository.save(new Seat(0,seatRequest.getSeat_number(),seatRequest.getSeat_type(),floor,false));
    }

    @Override
    public boolean floorExists(Integer office_id, Integer floor_number) {
        Office office = officeRepository.findById(office_id).orElse(null);
        if(office==null)return false;
        if(floorRepository.getFloorsByFloorNumberAndOfficeId(floor_number,office).size()>0)
        {
            return true;
        }
        return false;
    }
}
