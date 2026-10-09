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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

public class BidListServiceTests {

    private BidListRepository repository;
    private BidListService service;

    @BeforeEach
    void setUp() {
        repository = mock(BidListRepository.class);
        service = new BidListService(repository);
    }

    @Test
    void findById_throwsWhenIdDoesNotExist() {
        when(repository.findById(9999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findById(9999));

        assertEquals("Invalid BidList ID: 9999", exception.getMessage());
    }

    @Test
    void update_setsIdBeforeSaving() {
        BidList bid = new BidList();
        when(repository.save(bid)).thenReturn(bid);

        BidList result = service.update(5, bid);

        assertEquals(5, bid.getBidListId());
        assertSame(bid, result);
        verify(repository).save(bid);
    }

    @Test
    void findById_returnsBidLists() {

        BidList bidList = new BidList();

        when(repository.findById(1)).thenReturn(Optional.of(bidList));

        BidList result = service.findById(1);

        assertSame(bidList, result);
        verify(repository).findById(1);

    }

    @Test
    void deleteById_deletesBidList() {

        service.deleteById(1);

        verify(repository).deleteById(1);

    }

}
