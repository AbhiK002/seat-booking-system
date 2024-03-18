package com.project.sbs.api.controllers.user;

import com.project.sbs.api.requests.ChangePasswordRequest;
import com.project.sbs.api.requests.EditUserDetailsRequest;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.api.services.user.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final JwtService jwtService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, JwtService jwtService) {
        this.userProfileService = userProfileService;
        this.jwtService = jwtService;
    }

    @PatchMapping("/change-password")
    public SimpleResponse changePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        return null;
    }

    @PatchMapping("/edit-details")
    public SimpleResponse editDetails(
            @RequestHeader("Authorization") String token,
            @RequestBody EditUserDetailsRequest editUserDetailsRequest
    ) {
        return null;
    }
}
