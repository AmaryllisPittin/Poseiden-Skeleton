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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;

public class CurveServiceTests {

    private CurvePointRepository repository;
    private CurveService service;

    @BeforeEach
    void setUp() {
        repository = mock(CurvePointRepository.class);
        service = new CurveService(repository);
    }

    @Test
    void findById_throwsWhenIdDoesNotExist() {
        when(repository.findById(9999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findById(9999));

        assertEquals("Invalid CurvePoint ID: 9999", exception.getMessage());
    }

    @Test
    void update_setsIdBeforeSaving() {
        CurvePoint existing = new CurvePoint();
        CurvePoint updated = new CurvePoint(10, 2.0, 100.0);

        when(repository.findById(5)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        CurvePoint result = service.update(5, updated);

        assertEquals(10, existing.getCurveId());
        assertEquals(2.0, existing.getTerm());
        assertEquals(100.0, existing.getValue());
        assertSame(existing, result);

        verify(repository).save(existing);
    }

    @Test
    void findById_returnsCurvePoints() {

        CurvePoint curvePoint = new CurvePoint();

        when(repository.findById(1)).thenReturn(Optional.of(curvePoint));

        CurvePoint result = service.findById(1);

        assertSame(curvePoint, result);
        verify(repository).findById(1);

    }

    @Test
    void deleteById_deletesCurvePoint() {

        service.deleteById(1);

        verify(repository).deleteById(1);

    }

}
