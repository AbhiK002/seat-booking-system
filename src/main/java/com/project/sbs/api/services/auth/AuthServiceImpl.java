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
    private final PasswordService passwordService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordService = passwordService;
    }

    @Override
    public User registerNewUser(String userFullName, String userEmail, String userPassword) {
        userPassword = passwordService.hashPassword(userPassword);

        return userRepository.save(new User(0, userFullName, userEmail, userPassword, UserRole.USER.name()));
    }

    @Override
    public boolean userAlreadyExists(String userEmail) {
        return getUserDetails(userEmail) != null;
    }

    @Override
    public User loginUser(String userEmail, String userPassword) {
        User gotUser = getUserDetails(userEmail);
        if (gotUser != null) {
            if (passwordService.passwordMatchesHash(userPassword, gotUser.getUserPassword())) {
                return gotUser;
            }
        }

        return null;
    }

    @Override
    public User autoLoginUser(String token) {
        if (jwtService.isTokenValid(token)) {
            return getUserDetails(jwtService.getIdFromToken(token));
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
    public Integer getUserIdIfTokenValid(String token) {
        return jwtService.getIdFromToken(token);
    }
}
