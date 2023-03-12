package com.fpiacentini.challenge.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("singleton")
public class ThirdPartyPercentageCache {


    private int secondsToExpire;

    private Integer percentage;
    private LocalDateTime settingTime;

    public ThirdPartyPercentageCache(@Value("${cache.expire.seconds:1800}") int secondsToExpire){
        this.secondsToExpire = secondsToExpire;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
        this.updateRetrievedAt();
    }

    public void updateRetrievedAt() {
        this.settingTime = LocalDateTime.now();
    }

    public boolean isExpired() {
        return this.settingTime != null && LocalDateTime.now().isAfter(this.settingTime.plusSeconds(secondsToExpire));
    }

    public boolean hasBeenSet() {
        return this.percentage != null;
    }
}
