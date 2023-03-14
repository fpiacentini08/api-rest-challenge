package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.model.ApiCallModel;
import com.fpiacentini.challenge.model.CustomPage;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.service.ApiCallServiceImpl;
import com.fpiacentini.challenge.service.CalculationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChallengeControllerTest {

    private static final NumbersToAdd numbersToAdd = new NumbersToAdd(2, 2);
    private static final Result result = new Result(15d);
    private final CalculationServiceImpl calculationServiceMock = mock(CalculationServiceImpl.class);
    private final ApiCallServiceImpl apiCallServiceMock = mock(ApiCallServiceImpl.class);

    @Test
    void givenNumbersToAdd_whenCalculateResult_shouldReturn200withResult() throws Throwable {
        var challengeController = new ChallengeController(calculationServiceMock, apiCallServiceMock);
        when(calculationServiceMock.addNumbersAndApplyPercentage(numbersToAdd)).thenReturn(result);

        var response = challengeController.calculateResult(numbersToAdd);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(result, response.getBody());
        verify(calculationServiceMock, times(1)).addNumbersAndApplyPercentage(numbersToAdd);
    }

    @Test
    void givenPageNumberAndPageSize_whenGetHistoricApiCalls_shouldReturn200withCustomPage() throws Throwable {
        var challengeController = new ChallengeController(calculationServiceMock, apiCallServiceMock);
        var responseCustomPage = new CustomPage<ApiCallModel>(new ArrayList<>(), 0, 0, 20);
        when(apiCallServiceMock.getApiCallHistory(0, 20)).thenReturn(responseCustomPage);

        var response = challengeController.getHistoricApiCalls(0, 20);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseCustomPage, response.getBody());
        verify(apiCallServiceMock, times(1)).getApiCallHistory(0, 20);
    }

}
