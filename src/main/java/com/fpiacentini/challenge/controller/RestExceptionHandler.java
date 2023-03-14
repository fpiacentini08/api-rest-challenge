package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoPercentageAvailableException.class)
    public ResponseEntity<Object> handleNoPercentageAvailableException(
            NoPercentageAvailableException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "The request cannot be processed right now because no percentage is available to execute the calculations. Please, try again later.");
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }
}