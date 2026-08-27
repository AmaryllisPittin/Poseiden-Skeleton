package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
public class AuthenticationTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void authenticationTest() {

        Authentication authenticationMock = mock(Authentication.class);

        when(authenticationMock.isAuthenticated()).thenReturn(true);

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(authenticationMock);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("fake@email.com", "fake_Password01"));

        assertNotNull(authentication);
        assertTrue(authentication.isAuthenticated());
    }
}
