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
    public SimpleResponse changePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        String oldPassword = changePasswordRequest.getUser_old_password();
        String newPassword = changePasswordRequest.getUser_new_password();

        if (oldPassword == null || newPassword == null) {
            return new ErrorResponse("Some error occurred (null)");
        }

        if (oldPassword.equals(newPassword)) {
            return new ErrorResponse("New password cannot be Old password");
        }

        boolean success = userProfileService.changePassword(userId, oldPassword, newPassword);

        if (!success) {
            return new ErrorResponse("Wrong current password");
        }

        return new SuccessBooleanResponse(true);
    }

    @PatchMapping("/edit-details")
    public SimpleResponse editDetails(
            @RequestHeader("Authorization") String token,
            @RequestBody EditUserDetailsRequest editUserDetailsRequest
    ) {
        Integer userId = authService.getUserIdIfTokenValid(token);
        if (userId == null) {
            return new ErrorResponse("Login expired, please login again");
        }

        String user_fullname = editUserDetailsRequest.getUser_fullname();

        if (user_fullname == null) {
            return new ErrorResponse("Some error occurred (null)");
        }

        boolean success = userProfileService.editDetails(userId, user_fullname.trim());
        if (!success) {
            return new ErrorResponse("Some error occurred (user)");
        }
        return new SuccessBooleanResponse(true);
    }
}
