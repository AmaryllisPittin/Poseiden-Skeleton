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
        CurvePoint curvePoint = new CurvePoint();
        when(repository.save(curvePoint)).thenReturn(curvePoint);

        CurvePoint result = service.update(5, curvePoint);

        assertEquals(5, curvePoint.getCurveId());
        assertSame(curvePoint, result);
        verify(repository).save(curvePoint);
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
