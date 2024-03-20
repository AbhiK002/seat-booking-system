package com.project.sbs;

import com.project.sbs.api.services.auth.AuthServiceImpl;
import com.project.sbs.api.services.auth.JwtService;
import com.project.sbs.api.services.auth.PasswordService;
import com.project.sbs.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testAuthService() {
        assertEquals(authService.userAlreadyExists("invalid-email"), null);

        assertEquals(authService.autoLoginUser("invalid-token"), null);
        assertEquals(authService.getUserIdIfTokenValid("invalid-token"), null);
    }
}
