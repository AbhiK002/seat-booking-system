package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.PasswordService;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
public class tempAdminCreate {

    private UserRepository userRepository;
    private PasswordService passwordService;

    public tempAdminCreate(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @PostMapping("/admin/create")
    public String createAdmin(
            @RequestHeader("Authorization") String secretCode,
            @RequestParam("name") String adminName,
            @RequestParam("email") String adminEmail,
            @RequestBody String adminPassword
    )
    {
        if (!secretCode.equals("b&^Rv5b*&N(*Y(b^e$%#V5&rb*N")) {
            return "Unauthorized";
        }

        String password= passwordService.hashPassword(adminPassword);
        userRepository.save(new User(
                0,
                adminName,
                adminEmail,
                password,
                "USER,ADMIN"
        ));
        return "Admin created";
    }
}
