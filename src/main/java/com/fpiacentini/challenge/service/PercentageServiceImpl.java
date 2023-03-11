package com.fpiacentini.challenge.service;

import java.util.random.RandomGenerator;

public class PercentageServiceImpl implements PercentageService{

    private static final RandomGenerator randomGenerator = RandomGenerator.getDefault();
    private static final int MIN_PERCENTAGE = 0;
    private static final int MAX_PERCENTAGE = 100;

    @Override
    public Integer getPercentage() {
        return randomGenerator.nextInt(MIN_PERCENTAGE,MAX_PERCENTAGE+1);
    }
}
