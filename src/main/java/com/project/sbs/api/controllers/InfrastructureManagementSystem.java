package com.project.sbs.api.controllers;

import com.project.sbs.api.responses.BookingResponse;
import com.project.sbs.api.responses.CreateOfficeResponse;
import com.project.sbs.api.services.InfrastructureManagementService;
import com.project.sbs.api.services.InfrastructureManagementServiceImpl;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Office;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfrastructureManagementSystem {
    private InfrastructureManagementService infrastructureManagementService;

    public InfrastructureManagementSystem(InfrastructureManagementService infrastructureManagementService) {
        this.infrastructureManagementService = infrastructureManagementService;
    }

    @GetMapping("/office")
    public CreateOfficeResponse createOffice(@RequestHeader("Authorization") String token,@RequestParam("paramName") String OfficeName)
    {
        Office office= InfrastructureManagementServiceImpl.CreateOffice(OfficeName);

        return new CreateOfficeResponse(office,true);

    }

}
