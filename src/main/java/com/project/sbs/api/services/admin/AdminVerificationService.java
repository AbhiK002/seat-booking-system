package com.project.sbs.api.services.admin;

import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminVerificationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public AdminVerificationService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public boolean isAdmin(String token) {
        if (!jwtService.isTokenValid(token)) return false;

        Integer userId = jwtService.getIdFromToken(token);
        User userDetails = userRepository.findById(userId).orElse(null);
        if (userDetails == null) {
            return false;
        }
        else {
            String[] roles = userDetails.getUserRoles().split(",");
            for (String role : roles) {
                if (role.equals("ADMIN")) {
                    return true;
                }
            }

            return false;
        }
    }
}
