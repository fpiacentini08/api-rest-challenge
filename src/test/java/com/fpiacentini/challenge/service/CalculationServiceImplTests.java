package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class CalculationServiceImplTests {
    private final PercentageServiceImpl percentageService = mock(PercentageServiceImpl.class);
    private final CalculationServiceImpl calculationService = new CalculationServiceImpl(percentageService);

    @Test
    void givenZeroAndZero_whenAddNumbersAndApplyPercentage_shouldReturnZero() throws Throwable {
        var numbersToAdd = new NumbersToAdd(0, 0);
        var result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        assertEquals(0, result.value());
        verify(percentageService, times(1)).getPercentage();
    }

    @Test
    void given5And5And10Percentage_whenAddNumbersAndApplyPercentage_shouldReturn11() throws Throwable {
        var numbersToAdd = new NumbersToAdd(5, 5);
        when(percentageService.getPercentage()).thenReturn(10);
        var result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        assertEquals(11, result.value());
        verify(percentageService, times(1)).getPercentage();
    }

    @Test
    void given147And5523And99Percentage_whenAddNumbersAndApplyPercentage_shouldReturn13dot44() throws Throwable {
        var numbersToAdd = new NumbersToAdd(147, 5523);
        when(percentageService.getPercentage()).thenReturn(99);
        var result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        assertEquals(11283.3, result.value());
        verify(percentageService, times(1)).getPercentage();
    }

    @Test
    void given7And12And42Percentage_whenAddNumbersAndApplyPercentage_shouldReturn13dot44() throws Throwable {
        var numbersToAdd = new NumbersToAdd(7, 12);
        when(percentageService.getPercentage()).thenReturn(42);
        var result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        assertEquals(26.98, result.value());
        verify(percentageService, times(1)).getPercentage();
    }
}
