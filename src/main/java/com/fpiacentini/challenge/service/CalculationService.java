package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;

public interface CalculationService {

    Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd) throws Throwable;
}
