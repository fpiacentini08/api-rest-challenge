package com.fpiacentini.challenge.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThirdPartyPercentageCacheTests {

    @Test
    void givenEmptyCache_whenHasBeenSet_shouldReturnFalse() {
        ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache(1800);
        assertFalse(thirdPartyPercentageCache.hasBeenSet());
    }

    @Test
    void givenEmptyCache_whenIsExpired_shouldReturnTrue() {
        ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache(1800);
        assertTrue(thirdPartyPercentageCache.isExpired());
    }

    @Test
    void givenNonEmptyCache_whenHasBeenSet_shouldReturnTrue() {
        ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache(1800);
        thirdPartyPercentageCache.setPercentage(20);
        assertTrue(thirdPartyPercentageCache.hasBeenSet());
    }

    @Test
    void givenNonEmptyCache_whenGetPercentage_shouldReturnPercentage() {
        ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache(1800);
        thirdPartyPercentageCache.setPercentage(20);
        assertEquals(20, thirdPartyPercentageCache.getPercentage());
    }

    @Test
    void givenNonEmptyCacheWithExpiringTimeGreaterThanZero_whenIsExpired_shouldReturnFalse() {
        ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache(1800);
        thirdPartyPercentageCache.setPercentage(20);
        assertFalse(thirdPartyPercentageCache.isExpired());
    }

    @Test
    void givenNonEmptyCacheWithExpiringTimeEqualsToZero_whenIsExpired_shouldReturnTrue() {
        ThirdPartyPercentageCache thirdPartyPercentageCache = new ThirdPartyPercentageCache(0);
        thirdPartyPercentageCache.setPercentage(20);
        assertTrue(thirdPartyPercentageCache.isExpired());
    }

}
