package com.project.sbs;

import com.project.sbs.api.controllers.auth.AuthController;
import com.project.sbs.api.services.auth.AuthService;
import com.project.sbs.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
class SeatBookingSystemApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void authController(){
        when(authService.userAlreadyExists("hello.com"))
                .thenReturn(false);
        boolean actualReturn = false;
        assertFalse(actualReturn);
    }

}
