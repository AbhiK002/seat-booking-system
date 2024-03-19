package com.project.sbs.api.controllers.user;

import com.project.sbs.api.requests.ChangePasswordRequest;
import com.project.sbs.api.requests.EditUserDetailsRequest;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.responses.SuccessBooleanResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.api.services.user.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final AuthService authService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, AuthService authService) {
        this.userProfileService = userProfileService;
        this.authService = authService;
    }

    @PatchMapping("/change-password")
    public ResponseEntity<SimpleResponse> changePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        String oldPassword = changePasswordRequest.getUser_old_password();
        String newPassword = changePasswordRequest.getUser_new_password();

        if (oldPassword == null || newPassword == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Some error occurred (null)"));
        }

        if (oldPassword.equals(newPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("New password cannot be old password"));
        }

        boolean success = userProfileService.changePassword(userId, oldPassword, newPassword);

        if (!success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Wrong current password"));
        }

        return ResponseEntity.ok(new SuccessBooleanResponse(true));
    }

    @PatchMapping("/edit-details")
    public ResponseEntity<SimpleResponse> editDetails(
            @RequestHeader("Authorization") String token,
            @RequestBody EditUserDetailsRequest editUserDetailsRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please login again"));
        }

        String userFullname = editUserDetailsRequest.getUser_fullname();

        if (userFullname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Some error occurred (null)"));
        }

        boolean success = userProfileService.editDetails(userId, userFullname.trim());
        if (!success) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Some error occurred (user)"));
        }
        return ResponseEntity.ok(new SuccessBooleanResponse(true));
    }
}
