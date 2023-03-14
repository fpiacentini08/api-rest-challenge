package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    final static String MESSAGE = "message";
    final static String TIMESTAMP = "timestamp";
    final static String INVALID_PARAM_AND_REASON = "Invalid parameter:%s Reason:%s ";

    @ExceptionHandler(NoPercentageAvailableException.class)
    public ResponseEntity<Object> handleNoPercentageAvailableException(
            NoPercentageAvailableException ex, WebRequest request) {
        var body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, "The request cannot be processed right now because no percentage is available to execute the calculations. Please, try again later.");
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {

        var errors = new StringBuilder("");
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(String.format(INVALID_PARAM_AND_REASON, error.getField(), error.getDefaultMessage()));
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.append(String.format(INVALID_PARAM_AND_REASON, error.getObjectName(), error.getDefaultMessage()));
        }
        var body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, errors.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        var body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}