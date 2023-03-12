package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.cache.ThirdPartyPercentageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class PercentageServiceImpl implements PercentageService {

    private ThirdPartyPercentageService thirdPartyPercentageService;
    private ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache();

    public PercentageServiceImpl(@Autowired ThirdPartyPercentageService thirdPartyPercentageService) {
        this.thirdPartyPercentageService = thirdPartyPercentageService;
    }


    @Override
    public Integer getPercentage() {
        if (!thirdPartyPercentageCache.hasBeenSet() || thirdPartyPercentageCache.isExpired()) {
            updateCachedPercentage();
        }
        return thirdPartyPercentageCache.getPercentage();
    }

    private void updateCachedPercentage() {
        Integer percentage = thirdPartyPercentageService.getPercentage();
        thirdPartyPercentageCache.setPercentage(percentage);
        thirdPartyPercentageCache.updateRetrievedAt();
    }
}
