package com.project.sbs.api.services.auth;

import com.project.sbs.config.enums.UserRole;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public User registerNewUser(String userFullName, String userEmail, String userPassword) {
        // hash the password
        return userRepository.save(new User(0, userFullName, userEmail, userPassword, UserRole.USER.name()));
    }

    @Override
    public User loginUser(String userEmail, String userPassword) {
        User gotUser = getUserDetails(userEmail);
        if (gotUser != null) {
            // hash the password
            if (gotUser.getUserPassword().equals(userPassword)) {
                return gotUser;
            }
        }

        return null;
    }

    @Override
    public User autoLoginUser(String token) {
        if (jwtService.isTokenValid(token)) {
            return getUserDetails(jwtService.getIdFromValidToken(token));
        }

        return null;
    }

    @Override
    public User getUserDetails(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUserDetails(String userEmail) {
        return userRepository.getUserByUserEmail(userEmail);
    }

    @Override
    public boolean userExists(Integer userId) {
        return userRepository.existsById(userId);
    }
}
