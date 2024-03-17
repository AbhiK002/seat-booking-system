package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.responses.CreateFloorResponse;
import com.project.sbs.api.responses.CreateOfficeResponse;
import com.project.sbs.api.services.admin.InfrastructureManagementService;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfrastructureManagementSystem {
    private InfrastructureManagementService infrastructureManagementService;

    public InfrastructureManagementSystem(InfrastructureManagementService infrastructureManagementService) {
        this.infrastructureManagementService = infrastructureManagementService;
    }

    @PostMapping("/office")
    public CreateOfficeResponse createOffice(@RequestHeader("Authorization") String token, @RequestParam("office_name") String officeName)
    {
        Office office = infrastructureManagementService.createOffice(officeName);

        return new CreateOfficeResponse(office,true);
    }

    @PostMapping("/floor")
    public CreateFloorResponse CreateFloor(@RequestHeader("Authorization") String token,@RequestParam("floor_id") String floorId,@RequestParam("office_id") Office officeId)
    {
        Floor floor =infrastructureManagementService.createFloor(Integer.parseInt(floorId) ,officeId);
        return new CreateFloorResponse(floor,true);
    }
}
