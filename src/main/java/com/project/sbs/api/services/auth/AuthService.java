package com.project.sbs.api.services.auth;

import com.project.sbs.database.entities.User;

public interface AuthService {
    User registerNewUser(String userFullName, String userEmail, String userPassword);
    User loginUser(String userEmail, String userPassword);
    User autoLoginUser(String token);
    User getUserDetails(Integer userId);
    User getUserDetails(String userEmail);
    boolean userExists(Integer userId);
}
