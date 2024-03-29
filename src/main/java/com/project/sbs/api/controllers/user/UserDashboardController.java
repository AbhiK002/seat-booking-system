package com.project.sbs.api.controllers.user;

import com.project.sbs.api.requests.CancellationRequest;
import com.project.sbs.api.requests.ModifySwapRequest;
import com.project.sbs.api.responses.*;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.user.UserDashboardService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import com.project.sbs.database.entities.SwapRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserDashboardController {
    private AuthService authService;
    private UserDashboardService userDashboardService;

    @Autowired
    public UserDashboardController(AuthService authService, UserDashboardService userDashboardService) {
        this.authService = authService;
        this.userDashboardService = userDashboardService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<SimpleResponse> getBookings(@RequestHeader("Authorization") String token) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        List<Booking> bookingsList = userDashboardService.getBookings(userId);

        if (bookingsList == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Some error occurred (null)"));
        }

        return ResponseEntity.ok(new AnyListResponse<>(bookingsList, true));
    }

    @GetMapping("/cancellations")
    public ResponseEntity<SimpleResponse> getCancellations(@RequestHeader("Authorization") String token) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        List<Cancellation> cancellationsList = userDashboardService.getCancellations(userId);

        if (cancellationsList == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Some error occurred (null)"));
        }

        return ResponseEntity.ok(new AnyListResponse<>(cancellationsList, true));
    }

    @PostMapping("/cancellation")
    public ResponseEntity<SimpleResponse> makeCancellation(
            @RequestHeader("Authorization") String token,
            @RequestBody CancellationRequest cancellationRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        Integer bookingId = cancellationRequest.getBooking_id();
        if (bookingId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Booking ID is required"));
        }

        Cancellation newCancellation = userDashboardService.makeCancellation(userId, bookingId);

        if (newCancellation == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Some error occurred (refresh)"));
        }

        return ResponseEntity.ok(new AnyObjectResponse<>(newCancellation, true));
    }

    @GetMapping("/incoming-swap-requests")
    public ResponseEntity<SimpleResponse> getSwapRequests(
            @RequestHeader("Authorization") String token
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        List<SwapRequest> swapRequests = userDashboardService.findAllSwapRequests(userId);
        return ResponseEntity.ok(new AnyListResponse<>(swapRequests, true));
    }

    @PostMapping("/modify-swap-request")
    public ResponseEntity<SimpleResponse> modifySwapRequest(
            @RequestHeader("Authorization") String token,
            @RequestBody ModifySwapRequest modifySwapRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        SuccessBooleanResponse result = userDashboardService.modifySwapRequest(modifySwapRequest);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Swap Request does not exist"));
        }
        return ResponseEntity.ok(result);
    }
}
