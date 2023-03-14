package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.client.ThirdPartyPercentageServiceHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThirdPartyPercentageServiceImpl implements ThirdPartyPercentageService {

    private final ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient;

    public ThirdPartyPercentageServiceImpl(@Autowired ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient) {
        this.thirdPartyPercentageServiceHttpClient = thirdPartyPercentageServiceHttpClient;
    }

    public Optional<Integer> getPercentage() {
        Integer percentage = null;
        try {
            percentage = thirdPartyPercentageServiceHttpClient.getPercentage();
        } catch (Throwable e) {
//            Do nothing
        }
        return Optional.ofNullable(percentage);
    }
}
