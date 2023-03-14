package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.cache.ThirdPartyPercentageCache;
import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class PercentageServiceImpl implements PercentageService {

    private final ThirdPartyPercentageService thirdPartyPercentageService;
    private final ThirdPartyPercentageCache thirdPartyPercentageCache;

    public PercentageServiceImpl(@Autowired ThirdPartyPercentageService thirdPartyPercentageService, ThirdPartyPercentageCache thirdPartyPercentageCache) {
        this.thirdPartyPercentageService = thirdPartyPercentageService;
        this.thirdPartyPercentageCache = thirdPartyPercentageCache;
    }


    @Override
    public Integer getPercentage() throws Throwable {
        if (!thirdPartyPercentageCache.hasBeenSet() || thirdPartyPercentageCache.isExpired()) {
            updateCachedPercentage();
        }
        return thirdPartyPercentageCache.getPercentage();
    }

    private void updateCachedPercentage() throws Throwable {
        var percentage = thirdPartyPercentageService.getPercentage();
        if(percentage.isEmpty()){
            if(thirdPartyPercentageCache.hasBeenSet()){
                return;
            }
            throw new NoPercentageAvailableException();
        }
        thirdPartyPercentageCache.setPercentage(percentage.get());
    }
}
