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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

public class TradeServiceTests {

    private TradeRepository repository;
    private TradeService service;

    @BeforeEach
    void setUp() {
        repository = mock(TradeRepository.class);
        service = new TradeService(repository);
    }

    @Test
    void findById_throwsWhenIdDoesNotExist() {
        when(repository.findById(9999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findById(9999));

        assertEquals("Invalid trade ID: 9999", exception.getMessage());
    }

    @Test
    void update_setsIdBeforeSaving() {
        Trade trade = new Trade();
        when(repository.save(trade)).thenReturn(trade);

        Trade result = service.update(5, trade);

        assertEquals(5, trade.getTradeId());
        assertSame(trade, result);
        verify(repository).save(trade);
    }

}
