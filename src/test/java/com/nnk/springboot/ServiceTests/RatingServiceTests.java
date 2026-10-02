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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

public class RatingServiceTests {

    private RatingRepository repository;
    private RatingService service;

    @BeforeEach
    void setUp() {
        repository = mock(RatingRepository.class);
        service = new RatingService(repository);
    }

    @Test
    void findById_throwsWhenIdDoesNotExist() {
        when(repository.findById(9999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findById(9999));

        assertEquals("Invalid rating ID: 9999", exception.getMessage());
    }

    @Test
    void update_setsIdBeforeSaving() {
        Rating rating = new Rating();
        when(repository.save(rating)).thenReturn(rating);

        Rating result = service.update(5, rating);

        assertEquals(5, rating.getRatingId());
        assertSame(rating, result);
        verify(repository).save(rating);
    }
}
