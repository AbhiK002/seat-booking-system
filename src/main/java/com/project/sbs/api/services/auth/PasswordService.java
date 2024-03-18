package com.project.sbs.api.services.auth;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class PasswordService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean passwordMatchesHash(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
