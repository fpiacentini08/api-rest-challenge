package com.fpiacentini.challenge.cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("singleton")
public class ThirdPartyPercentageCache {

    
    private static final int SECONDS_TO_EXPIRE = 1800;

    private Integer percentage;
    private LocalDateTime settingTime;

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
        return this.settingTime != null && LocalDateTime.now().isAfter(this.settingTime.plusSeconds(SECONDS_TO_EXPIRE));
    }

    public boolean hasBeenSet() {
        return this.percentage != null;
    }
}
