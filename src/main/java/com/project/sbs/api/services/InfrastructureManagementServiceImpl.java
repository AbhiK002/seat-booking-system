package com.project.sbs.api.services;


import com.project.sbs.database.entities.Office;
import com.project.sbs.database.repositories.OfficeRepository;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureManagementServiceImpl implements InfrastructureManagementService{

    private final OfficeRepository officeRepository;

    public InfrastructureManagementServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public Office createOffice(String OfficeName) {
        return officeRepository.save(new Office(0, OfficeName));
    }
}
