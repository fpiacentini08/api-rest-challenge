package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.service.ApiCallService;
import com.fpiacentini.challenge.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("challenge")
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
    public ResponseEntity<Result> calculateResult(@RequestBody NumbersToAdd numbersToAdd) throws Throwable {
        Result result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        apiCallService.createApiCallHistory(numbersToAdd, result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
