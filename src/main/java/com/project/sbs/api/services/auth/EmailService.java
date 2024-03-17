package com.project.sbs.api.services.auth;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    public int generateOTP() {
        int OTP = new Random().nextInt(1000, 9999);
        System.out.println("Generated OTP: " + OTP);

        // a fixed OTP because we're not actually sending emails
        // therefore no way to tell the user the OTP
        return 3548;
    }

    public boolean sendEmail(String to, int OTP) {
        // email the given email address with the OTP
        // we'd need to buy a domain to actually send emails
        // assume the email was sent to the user with the OTP
        String emailBody = "The verification OTP for Seat Booking System is: " + OTP;
        return true;
    }
}
