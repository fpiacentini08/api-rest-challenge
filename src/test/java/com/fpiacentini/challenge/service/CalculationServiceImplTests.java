package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationServiceImplTests {

    private final CalculationServiceImpl calculationService = new CalculationServiceImpl();

    @Test
    void givenZeroAndZero_whenAddNumbersAndApplyPercentage_shouldReturnZero(){
        NumbersToAdd numbersToAdd = new NumbersToAdd(0,0);
        Result result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        assertEquals(0, result.value());
    }

    @Test
    void givenAnyNumberAndTheSameButNegative_whenAddNumbersAndApplyPercentage_shouldReturnZero(){
        Integer number = 345;
        NumbersToAdd numbersToAdd = new NumbersToAdd(number,-number);
        Result result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        assertEquals(0, result.value());
    }

}
