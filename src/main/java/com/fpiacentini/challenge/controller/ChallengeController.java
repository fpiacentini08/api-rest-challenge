package com.fpiacentini.challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import com.fpiacentini.challenge.model.ApiCallModel;
import com.fpiacentini.challenge.model.CustomPage;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.service.ApiCallService;
import com.fpiacentini.challenge.service.CalculationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("challenge")
@Validated
public class ChallengeController {

    CalculationService calculationService;

    ApiCallService apiCallService;


    @Autowired
    public ChallengeController(CalculationService calculationService, ApiCallService apiCallService) {
        this.calculationService = calculationService;
        this.apiCallService = apiCallService;
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> calculateResult(@Valid @RequestBody NumbersToAdd numbersToAdd) throws NoPercentageAvailableException, JsonProcessingException {
        var result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        apiCallService.createApiCallHistory(numbersToAdd, result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomPage<ApiCallModel>> getHistoricApiCalls(
            @Min(value = 0, message = "Page number should be 0 or higher.") @RequestParam Integer pageNumber,
            @Min(value = 1, message = "Page size should be between 1 and 1000.")
            @Max(value = 1000, message = "Page size should be between 1 and 1000.") @RequestParam Integer pageSize) {
        var apiCallHistory = apiCallService.getApiCallHistory(pageNumber, pageSize);
        return new ResponseEntity<>(apiCallHistory, HttpStatus.OK);
    }

}
