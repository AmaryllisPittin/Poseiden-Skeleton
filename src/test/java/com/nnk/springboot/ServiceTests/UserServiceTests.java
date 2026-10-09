package com.nnk.springboot.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

public class UserServiceTests {

    private UserRepository repository;
    private PasswordEncoder encoder;
    private UserService service;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
        service = new UserService(repository, encoder);
    }

    @Test
    void findById_throwsWhenIdDoesNotExist() {
        when(repository.findById(9999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findById(9999));

        assertEquals("Invalid user ID: 9999", exception.getMessage());
    }

    @Test
    void update_setsIdBeforeSaving() {
        User user = new User();
        when(repository.save(user)).thenReturn(user);

        User result = service.update(5, user);

        assertEquals(5, user.getId());
        assertSame(user, result);
        verify(repository).save(user);
    }

    @Test
    void findById_returnsUser() {
        User user = new User();

        when(repository.findById(1)).thenReturn(Optional.of(user));

        User result = service.findById(1);

        assertSame(user, result);
        verify(repository).findById(1);

    }

}
