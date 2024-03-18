package com.project.sbs.api.services.user;

import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.PasswordService;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private PasswordService passwordService;
    private AuthService authService;
    private UserRepository userRepository;

    @Autowired
    public UserProfileService(PasswordService passwordService, AuthService authService, UserRepository userRepository) {
        this.passwordService = passwordService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public boolean changePassword(Integer userId, String userOldPassword, String userNewPassword) {
        User user = authService.getUserDetails(userId);

        if (user == null) return false;

        if (passwordService.passwordMatchesHash(userOldPassword, user.getUserPassword())) {
            User newUser = userRepository.save(new User(
                    userId,
                    user.getUserFullname(),
                    user.getUserEmail(),
                    passwordService.hashPassword(userNewPassword),
                    user.getUserRoles()
            ));

            return true;
        }

        return false;
    }

    public boolean areMatchingPasswords(String password, String hashedPassword) {
        return passwordService.passwordMatchesHash(password, hashedPassword);
    }

    public boolean editDetails(Integer userId, String newFullName) {
        User user = authService.getUserDetails(userId);

        if (user == null) return false;

        User newUser = userRepository.save(new User(
                userId,
                newFullName,
                user.getUserEmail(),
                user.getUserPassword(),
                user.getUserRoles()
        ));

        return true;
    }
}
