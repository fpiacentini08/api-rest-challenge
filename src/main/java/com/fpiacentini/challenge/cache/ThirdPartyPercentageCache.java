package com.fpiacentini.challenge.cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("singleton")
public class ThirdPartyPercentageCache {

    private static final int MINUTES_TO_EXPIRE = 30;

    private Integer percentage;
    private LocalDateTime settingTime;

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public void updateRetrievedAt() {
        this.settingTime = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.settingTime.plusMinutes(MINUTES_TO_EXPIRE));
    }

    public boolean hasBeenSet() {
        return this.percentage != null;
    }
}
