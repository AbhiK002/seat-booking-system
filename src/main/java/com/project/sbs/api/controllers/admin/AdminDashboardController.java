package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.requests.ModifyBooking;
import com.project.sbs.api.requests.ModifyCancellation;
import com.project.sbs.api.responses.*;
import com.project.sbs.api.services.admin.AdminBookingService;
import com.project.sbs.api.services.admin.AdminVerificationService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdminDashboardController {
    private final AdminBookingService adminBookingService;
    private final AdminVerificationService adminVerificationService;

    @Autowired
    public AdminDashboardController(AdminBookingService adminBookingService, AdminVerificationService adminVerificationService) {
        this.adminBookingService = adminBookingService;
        this.adminVerificationService = adminVerificationService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<SimpleResponse> getBookings(@RequestHeader("Authorization") String token) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }
        List<Booking> bookings = adminBookingService.getAllPendingBookings();
        return ResponseEntity.ok(new AnyListResponse<>(bookings, true));
    }


    @PatchMapping("/modify-booking")
    public ResponseEntity<SimpleResponse> modifyBooking(
            @RequestHeader("Authorization") String token,
            @RequestBody ModifyBooking modifyBooking
    ) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }
        Booking booking = adminBookingService.modifyBooking(modifyBooking);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Booking ID invalid"));
        }
        return ResponseEntity.ok(new AnyObjectResponse<>(booking, true));
    }

    @GetMapping("/cancellations")
    public ResponseEntity<SimpleResponse> getCancellations(@RequestHeader("Authorization") String token) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }
        List<Cancellation> cancellations = adminBookingService.getAllPendingCancellations();
        return ResponseEntity.ok(new AnyListResponse<>(cancellations, true));
    }

    @PatchMapping("/modify-cancellation")
    public ResponseEntity<SimpleResponse> modifyCancellation(
            @RequestHeader("Authorization") String token,
            @RequestBody ModifyCancellation modifyCancellation
    ) {
        if (!adminVerificationService.isAdmin(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Unauthorized"));
        }
        Cancellation cancellation = adminBookingService.modifyCancellation(modifyCancellation);
        if (cancellation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Cancellation ID invalid"));
        }
        return ResponseEntity.ok(new AnyObjectResponse<>(cancellation, true));
    }
}