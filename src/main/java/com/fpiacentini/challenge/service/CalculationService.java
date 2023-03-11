package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;

public interface CalculationService {

    public Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd);
}
