package com.project.sbs.api.services.user;

import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    public boolean changePassword(Integer userId, String userOldPassword, String userNewPassword) {
        return true;
    }

    public boolean editDetails(Integer userId, String newFullName) {
        return true;
    }
}
