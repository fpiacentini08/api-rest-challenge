package com.fpiacentini.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
@Scope("singleton")
public class PercentageServiceImpl implements PercentageService{

    private ThirdPartyPercentageService thirdPartyPercentageService;

    public PercentageServiceImpl(@Autowired ThirdPartyPercentageService thirdPartyPercentageService){
        this.thirdPartyPercentageService = thirdPartyPercentageService;
    }

    @Override
    public Integer getPercentage() {
        return thirdPartyPercentageService.getPercentage();
    }
}
