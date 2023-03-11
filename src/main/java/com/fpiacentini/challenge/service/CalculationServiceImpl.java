package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;

public class CalculationServiceImpl implements CalculationService{

    @Override
    public Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd) {
        return new Result(numbersToAdd.firstNumber().doubleValue() + numbersToAdd.secondNumber().doubleValue());
    }
}
