package com.project.sbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SeatBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatBookingSystemApplication.class, args);
    }

}
