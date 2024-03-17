package com.project.sbs.api.services;


import com.project.sbs.database.entities.Office;
import com.project.sbs.database.repositories.FloorRepository;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureManagementServiceImpl implements InfrastructureManagementService{

    private final Office office;

    public InfrastructureManagementServiceImpl(Office office) {
        this.office = office;
    }

    @Override
    public Office CreateOffice(String OfficeName) {
        office.setOfficeName(OfficeName);
        return office;
    }
}
