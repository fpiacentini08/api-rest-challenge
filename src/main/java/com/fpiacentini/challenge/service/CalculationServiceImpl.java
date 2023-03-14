package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequestScope
public class CalculationServiceImpl implements CalculationService{

    PercentageService percentageService;

    @Autowired
    public CalculationServiceImpl(PercentageService percentageService){
        this.percentageService = percentageService;
    }

    @Override
    public Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd) throws Throwable {
        var percentageToApply = new BigDecimal(percentageService.getPercentage() + 100);
        var firstNumber = new BigDecimal(numbersToAdd.firstNumber().doubleValue());
        var secondNumber = new BigDecimal(numbersToAdd.secondNumber().doubleValue());
        var resultValue = firstNumber.add(secondNumber).multiply(percentageToApply).divide(new BigDecimal(100));
        resultValue.setScale(2, RoundingMode.UNNECESSARY);
        return new Result(resultValue.doubleValue());
    }
}
