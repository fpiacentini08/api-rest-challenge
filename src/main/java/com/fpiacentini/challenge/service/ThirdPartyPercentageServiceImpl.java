package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.client.ThirdPartyPercentageServiceHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPercentageServiceImpl implements ThirdPartyPercentageService {

    private ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient;

    public ThirdPartyPercentageServiceImpl(@Autowired ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient) {
        this.thirdPartyPercentageServiceHttpClient = thirdPartyPercentageServiceHttpClient;
    }

    public Integer getPercentage() {
        return thirdPartyPercentageServiceHttpClient.getPercentage();
    }
}
