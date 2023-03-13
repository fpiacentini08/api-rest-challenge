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

    public CalculationServiceImpl(@Autowired PercentageService percentageService){
        this.percentageService = percentageService;
    }

    @Override
    public Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd) throws Throwable {
        BigDecimal percentageToApply = new BigDecimal(percentageService.getPercentage() + 100);
        BigDecimal firstNumber = new BigDecimal(numbersToAdd.firstNumber().doubleValue());
        BigDecimal secondNumber = new BigDecimal(numbersToAdd.secondNumber().doubleValue());
        BigDecimal resultValue = firstNumber.add(secondNumber).multiply(percentageToApply).divide(new BigDecimal(100));
        resultValue.setScale(2, RoundingMode.UNNECESSARY);
        return new Result(resultValue.doubleValue());
    }
}
