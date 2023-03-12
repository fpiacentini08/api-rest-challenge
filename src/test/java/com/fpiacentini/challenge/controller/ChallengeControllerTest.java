package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.service.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChallengeControllerTest {

    private static final NumbersToAdd numbersToAdd = new NumbersToAdd(2, 2);
    private static final Result result = new Result(15d);
    private final CalculationServiceImpl calculationServiceMock = mock(CalculationServiceImpl.class);

    @Test
    void givenNumbersToAdd_whenCalculateResult_shouldReturn200withResult() {
        ChallengeController challengeController = new ChallengeController(calculationServiceMock);
        when(calculationServiceMock.addNumbersAndApplyPercentage(numbersToAdd)).thenReturn(result);

        ResponseEntity<Result> response = challengeController.calculateResult(numbersToAdd);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(result, response.getBody());
        verify(calculationServiceMock, times(1)).addNumbersAndApplyPercentage(numbersToAdd);
    }
}
