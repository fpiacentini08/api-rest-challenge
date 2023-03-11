package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
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

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> create(@RequestBody NumbersToAdd numbersToAdd) {
        Result result = new Result(numbersToAdd.firstNumber().doubleValue() + numbersToAdd.secondNumber().doubleValue());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
