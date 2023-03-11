package com.fpiacentini.challenge.mock;

import com.fpiacentini.challenge.service.ThirdPartyPercentageService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
@Scope("singleton")
public class ThirdPartyPercentageServiceMock implements ThirdPartyPercentageService {

    private static final RandomGenerator randomGenerator = RandomGenerator.getDefault();
    private static final int MIN_PERCENTAGE = 0;
    private static final int MAX_PERCENTAGE = 100;

    @Override
    public Integer getPercentage() {
        return randomGenerator.nextInt(MIN_PERCENTAGE,MAX_PERCENTAGE+1);
    }

}
