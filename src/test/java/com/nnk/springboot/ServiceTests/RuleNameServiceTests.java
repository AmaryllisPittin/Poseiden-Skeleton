package com.nnk.springboot.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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

    @Test
    void findAll_returnsAllRuleNames() {

        RuleName ruleName1 = new RuleName();
        RuleName ruleName2 = new RuleName();

        List<RuleName> ruleNames = List.of(ruleName1, ruleName2);

        when(repository.findAll()).thenReturn(ruleNames);

        List<RuleName> result = service.findAll();

        assertEquals(ruleNames, result);
        verify(repository).findAll();

    }

    @Test
    void findById_returnsRuleNames() {

        RuleName ruleName = new RuleName();

        when(repository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName result = service.findById(1);

        assertSame(ruleName, result);
        verify(repository).findById(1);

    }

    @Test
    void deleteById_deletesRuleName() {

        service.deleteById(1);

        verify(repository).deleteById(1);

    }

}
