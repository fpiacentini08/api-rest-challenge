package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.client.ThirdPartyPercentageServiceHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPercentageServiceImpl implements ThirdPartyPercentageService {

    private final ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient;

    public ThirdPartyPercentageServiceImpl(@Autowired ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient) {
        this.thirdPartyPercentageServiceHttpClient = thirdPartyPercentageServiceHttpClient;
    }

    public Integer getPercentage() throws Throwable {
        return thirdPartyPercentageServiceHttpClient.getPercentage();
    }
}
