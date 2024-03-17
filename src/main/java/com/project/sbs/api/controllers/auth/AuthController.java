package com.project.sbs.api.controllers.auth;

import com.project.sbs.api.requests.RegisterRequest;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.LoginSuccessfulResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.EmailService;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.config.response_components.UserData;
import com.project.sbs.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthService authService;
    JwtService jwtService;
    EmailService emailService;

    @Autowired
    public AuthController(AuthService authService, JwtService jwtService, EmailService emailService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public SimpleResponse registerUser(
            @RequestBody RegisterRequest userDetails
    ) {
        String user_fullname = userDetails.getUser_fullname().trim();
        String user_email = userDetails.getUser_email().trim();
        String user_password = userDetails.getUser_password();
        String user_otp = userDetails.getUser_otp().trim();

        if (
                user_fullname.isEmpty()
                || user_email.isEmpty()
                || user_password.isEmpty()
                || user_otp.isEmpty()
        ) {
            return new ErrorResponse("No empty fields allowed");
        }

        if (authService.userAlreadyExists(user_email)) {
            return new ErrorResponse("Email already registered");
        }

        if (!user_otp.equals(String.valueOf(emailService.generateOTP()))) {
            return new ErrorResponse("Wrong OTP");
        }

        User newUser = authService.registerNewUser(user_fullname, user_email, user_password);
        if (newUser != null) {
            return new LoginSuccessfulResponse(
                    new UserData(
                            newUser.getUserId(),
                            newUser.getUserFullname(),
                            newUser.getUserEmail(),
                            newUser.getUserRoles()
                    ),
                    jwtService.generateToken(newUser.getUserId()),
                    true
            );
        }
        else {
            return new ErrorResponse("Some error occurred");
        }
    }
}
