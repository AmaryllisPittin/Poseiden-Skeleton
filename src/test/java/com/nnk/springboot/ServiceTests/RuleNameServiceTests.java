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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

public class RuleNameServiceTests {

    private RuleNameRepository repository;
    private RuleNameService service;

    @BeforeEach
    void setUp() {
        repository = mock(RuleNameRepository.class);
        service = new RuleNameService(repository);
    }

    @Test
    void findById_throwsWhenIdDoesNotExist() {
        when(repository.findById(9999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.findById(9999));

        assertEquals("Invalid ruleName ID: 9999", exception.getMessage());
    }

    @Test
    void update_setsIdBeforeSaving() {
        RuleName ruleName = new RuleName();
        when(repository.save(ruleName)).thenReturn(ruleName);

        RuleName result = service.update(5, ruleName);

        assertEquals(5, ruleName.getRuleNameId());
        assertSame(ruleName, result);
        verify(repository).save(ruleName);
    }

}
