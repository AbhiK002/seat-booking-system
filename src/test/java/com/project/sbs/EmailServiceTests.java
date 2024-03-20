package com.project.sbs;

import com.project.sbs.api.controllers.auth.AuthController;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.EmailService;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTests {

    private final EmailService emailService = new EmailService();

// testing Auth service

    @Test
    void testIsValidEmail_ValidEmail() {
        String validEmail = "user@example.com";
        assertTrue(emailService.isValidEmail(validEmail));
    }

    @Test
    void testIsValidEmail_InvalidEmail() {
        String invalidEmail = "invalid-email";
        assertFalse(emailService.isValidEmail(invalidEmail));
    }

    @Test
    void generateOTP_ShouldGenerateOTPWithinRange() {
        EmailService emailService = new EmailService();
        int generatedOTP = emailService.generateOTP();
        assertTrue(generatedOTP >= 1000 && generatedOTP <= 9999, "Generated OTP is within the valid range");
    }
}
