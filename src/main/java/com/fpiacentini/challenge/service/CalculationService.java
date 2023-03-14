package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;

public interface CalculationService {

    Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd) throws NoPercentageAvailableException;
}
