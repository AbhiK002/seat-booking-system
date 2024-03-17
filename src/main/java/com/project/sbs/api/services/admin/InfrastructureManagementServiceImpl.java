package com.project.sbs.api.services.admin;


import com.project.sbs.api.services.admin.InfrastructureManagementService;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.repositories.FloorRepository;
import com.project.sbs.database.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service


public class InfrastructureManagementServiceImpl implements InfrastructureManagementService {

    private final OfficeRepository officeRepository;
    private final FloorRepository floorRepository;

    @Autowired
    public InfrastructureManagementServiceImpl(OfficeRepository officeRepository, FloorRepository floorRepository) {
        this.officeRepository = officeRepository;
        this.floorRepository = floorRepository;
    }

    @Override
    public Office createOffice(String OfficeName) {
        return officeRepository.save(new Office(0, OfficeName));
    }

    @Override
    public Floor createFloor(Integer floorNumber, Office officeId) {

        return floorRepository.save(new Floor(0,floorNumber,officeId));
    }
}
