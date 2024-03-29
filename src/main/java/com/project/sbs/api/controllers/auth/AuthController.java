package com.project.sbs.api.controllers.auth;

import com.project.sbs.api.requests.LoginRequest;
import com.project.sbs.api.requests.RegisterRequest;
import com.project.sbs.api.requests.VerifyEmailRequest;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.LoginSuccessfulResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.responses.SuccessBooleanResponse;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.EmailService;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.config.response_components.UserData;
import com.project.sbs.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

    @GetMapping("/hello")
    public String getHello() {
        return "Hello Sukhad";
    }

    @GetMapping("/")
    public ResponseEntity<String> getRootMessage() {
        return new ResponseEntity<>("the message", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> registerUser(
            @RequestBody RegisterRequest userDetails
    ) {
        String user_fullname = userDetails.getUser_fullname();
        String user_email = userDetails.getUser_email();
        String user_password = userDetails.getUser_password();
        String user_otp = userDetails.getUser_otp();

        if (user_fullname==null || user_email==null || user_password==null || user_otp==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Something went wrong (null)"));
        }

        user_fullname = user_fullname.trim();
        user_email = user_email.trim();
        user_otp = user_otp.trim();

        if (user_fullname.isEmpty() || user_email.isEmpty() || user_password.isEmpty() || user_otp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Empty fields are not allowed"));
        }

        if (!emailService.isValidEmail(user_email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid email"));
        }

        if (authService.userAlreadyExists(user_email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Email already registered"));
        }

        if (!user_otp.equals(String.valueOf(emailService.generateOTP()))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Wrong OTP"));
        }

        User newUser = authService.registerNewUser(user_fullname, user_email, user_password);
        if (newUser != null) {
            return ResponseEntity.ok(new LoginSuccessfulResponse(
                    new UserData(newUser),
                    jwtService.generateToken(newUser.getUserId()),
                    true
            ));
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Some error occurred"));
        }
    }


    @PostMapping("/verify-email")
    public ResponseEntity<SimpleResponse> verifyEmail(
            @RequestBody VerifyEmailRequest emailRequest
    ) {
        String email = emailRequest.getEmail();

        if (email == null || !emailService.isValidEmail(email)) {
            return new ResponseEntity<>(new ErrorResponse("Invalid email"), HttpStatus.BAD_REQUEST);
        }

        int OTP = emailService.generateOTP();
        emailService.sendEmail(email, OTP);
        return new ResponseEntity<>(new SuccessBooleanResponse(true), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<SimpleResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        String user_email = loginRequest.getUser_email();
        String user_password = loginRequest.getUser_password();

        if (user_email == null || user_password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Something went wrong (null)"));
        }

        user_email = user_email.trim();

        if (user_password.isEmpty() || user_email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Empty fields are not allowed"));
        }
        if (!emailService.isValidEmail(user_email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid email"));
        }

        User loggedInUser = authService.loginUser(user_email, user_password);
        if (loggedInUser != null) {
            return ResponseEntity.ok(new LoginSuccessfulResponse(
                    new UserData(loggedInUser),
                    jwtService.generateToken(loggedInUser.getUserId()),
                    true
            ));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid credentials"));
        }
    }


    @PostMapping("/autologin")
    public ResponseEntity<SimpleResponse> autoLogin(@RequestHeader("Authorization") String token) {
        if (jwtService.isTokenValid(token)) {
            User loggedInUser = authService.autoLoginUser(token);
            if (loggedInUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Login expired, please log in again"));
            }

            return ResponseEntity.ok(new LoginSuccessfulResponse(
                    new UserData(loggedInUser),
                    token,
                    true
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Login expired, please log in again"));
        }
    }
}
