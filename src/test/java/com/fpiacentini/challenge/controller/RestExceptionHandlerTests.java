package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.junit.Assert.assertEquals;

public class RestExceptionHandlerTests {

    @Test
    void givenNoPercentageAvailableException_whenHandleNoPercentageAvailableException_shouldReturn503(){
        var restExceptionHandler = new RestExceptionHandler();
        var response = restExceptionHandler.handleNoPercentageAvailableException(new NoPercentageAvailableException(), null);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }
}
