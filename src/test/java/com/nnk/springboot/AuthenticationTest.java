package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
public class AuthenticationTest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    public void authenticationTest() {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("test@test.com", "Password_2345"));

        assertNotNull(authentication);
        assertTrue(authentication.isAuthenticated());
    }
}
