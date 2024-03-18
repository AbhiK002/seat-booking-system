package com.project.sbs.api.controllers.admin;

import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.api.services.auth.PasswordService;
import com.project.sbs.database.entities.User;
import com.project.sbs.database.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class tempAdminCreate {

    private UserRepository userRepository;
    private PasswordService passwordService;

    public tempAdminCreate(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @PostMapping("/admin")
    public String createAdmin()
    {
        String password= passwordService.hashPassword("123456789");
        User admin=userRepository.save(new User(0,"ali hassan","ali@example.com",password,"ADMIN"));
        return "Admin created ";
    }
}
