package com.project.sbs.api.services.admin;

import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminVerificationService {
    private final UserRepository userRepository;

    public AdminVerificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isAdmin(Integer userId) {
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
