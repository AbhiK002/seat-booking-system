package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.requests.SeatList;
import com.project.sbs.api.requests.SeatRequest;
import com.project.sbs.api.responses.*;
import com.project.sbs.api.services.admin.AdminVerificationService;
import com.project.sbs.api.services.admin.InfrastructureManagementService;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfrastructureManagementSystem {
    private InfrastructureManagementService infrastructureManagementService;
    private AdminVerificationService adminVerificationService;

    @Autowired
    public InfrastructureManagementSystem(InfrastructureManagementService infrastructureManagementService,AdminVerificationService adminVerificationService) {
        this.infrastructureManagementService = infrastructureManagementService;
        this.adminVerificationService= adminVerificationService;
    }


    @PostMapping("/office")
    public SimpleResponse createOffice(@RequestHeader("Authorization") String token,
                                       @RequestParam("office_name") String officeName)
    {
        if(!adminVerificationService.isAdmin(token))
        {
            return new ErrorResponse("Unauthorised");
        }
        Office office = infrastructureManagementService.createOffice(officeName);

        return new CreateOfficeResponse(office,true);
    }

    @PostMapping("/floor")
    public SimpleResponse CreateFloor(@RequestHeader("Authorization") String token,
                                      @RequestParam("floor_number") String floorNumber,
                                      @RequestParam("office_id") Integer officeId)
    {
        if(!adminVerificationService.isAdmin(token))
        {
            return new ErrorResponse("Unauthorised");
        }

        Floor floor =infrastructureManagementService.createFloor(Integer.parseInt(floorNumber) ,officeId);
        return new CreateFloorResponse(floor,true);
    }

    @PostMapping("/seats")
    public SimpleResponse CreateSeats(
            @RequestHeader("Authorization") String token,
            @RequestBody SeatList seatlist
            )
    {
        if(!adminVerificationService.isAdmin(token))
        {
            return new ErrorResponse("Unauthorised");
        }
        for(SeatRequest seat:seatlist.getSeats())
        {
            Seat newSeat=infrastructureManagementService.CreateSeat(seat);
            if(newSeat==null)
            {
                return new ErrorResponse("Floor does not exist ");
            }
        }
        return new SuccessBooleanResponse(true);


    }
}
