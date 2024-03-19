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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class InfrastructureManagementSystem {
    private InfrastructureManagementService infrastructureManagementService;
    private AdminVerificationService adminVerificationService;

    @Autowired
    public InfrastructureManagementSystem(InfrastructureManagementService infrastructureManagementService,AdminVerificationService adminVerificationService) {
        this.infrastructureManagementService = infrastructureManagementService;
        this.adminVerificationService= adminVerificationService;
    }

    @PostMapping("/office")
    public ResponseEntity<SimpleResponse> createOffice(
            @RequestHeader("Authorization") String token,
            @RequestParam("office_name") String officeName
    ) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }
        Office office = infrastructureManagementService.createOffice(officeName);
        return ResponseEntity.ok(new CreateOfficeResponse(office, true));
    }

    @PostMapping("/floor")
    public ResponseEntity<SimpleResponse> createFloor(
            @RequestHeader("Authorization") String token,
            @RequestParam("floor_number") String floorNumber,
            @RequestParam("office_id") Integer officeId
    ) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }

        Floor floor = infrastructureManagementService.createFloor(Integer.parseInt(floorNumber), officeId);
        if (floor == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Floor already exists"));
        }
        return ResponseEntity.ok(new CreateFloorResponse(floor, true));
    }

    @PostMapping("/seats")
    public ResponseEntity<SimpleResponse> createSeats(
            @RequestHeader("Authorization") String token,
            @RequestBody SeatList seatList
    ) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }
        for (SeatRequest seat : seatList.getSeats()) {
            Seat newSeat = infrastructureManagementService.CreateSeat(seat);
            if (newSeat == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Floor does not exist"));
            }
        }
        return ResponseEntity.ok(new SuccessBooleanResponse(true));
    }
}
