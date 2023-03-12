package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
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

    public ChallengeController(@Autowired CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> calculateResult(@RequestBody NumbersToAdd numbersToAdd) {
        Result result = calculationService.addNumbersAndApplyPercentage(numbersToAdd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
