package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
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
    public Result addNumbersAndApplyPercentage(NumbersToAdd numbersToAdd) throws NoPercentageAvailableException {
        var percentageToApply = percentageService.getPercentage();
        var resultValue = makeCalculation( numbersToAdd, percentageToApply);
        return new Result(resultValue);
    }

    private Double makeCalculation(NumbersToAdd numbersToAdd, Integer percentage){
        var firstNumber = new BigDecimal(numbersToAdd.firstNumber().doubleValue());
        var secondNumber = new BigDecimal(numbersToAdd.secondNumber().doubleValue());
        var resultValue = firstNumber.add(secondNumber).multiply(new BigDecimal(percentage + 100)).divide(new BigDecimal(100));
        resultValue.setScale(2, RoundingMode.UNNECESSARY);
        return resultValue.doubleValue();
    }
}
