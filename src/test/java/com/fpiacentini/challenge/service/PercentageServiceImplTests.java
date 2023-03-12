package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.cache.ThirdPartyPercentageCache;
import com.fpiacentini.challenge.mock.ThirdPartyPercentageServiceMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PercentageServiceImplTests {
    private final ThirdPartyPercentageServiceMock thirdPartyPercentageServiceMock = mock(ThirdPartyPercentageServiceMock.class);

    @Test
    void givenThirdPartyServiceResponse_whenGetPercentage_shouldReturnTheSameNumber() {
        final ThirdPartyPercentageCache thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(1800);
        final PercentageServiceImpl percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(20);
        Integer percentage = percentageService.getPercentage();
        assertEquals(20, percentage);
    }

    @Test
    void givenCachedValueNotExpired_whenGetPercentage_shouldReturnTheCachedNumber() {
        final ThirdPartyPercentageCache thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(1800);
        final PercentageServiceImpl percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(20);
        thirdPartyPercentageCacheMock.setPercentage(75);
        Integer percentage = percentageService.getPercentage();
        assertEquals(75, percentage);
    }

    @Test
    void givenCachedValueExpired_whenGetPercentage_shouldReturnTheThirdPartyServiceReturnedNumber() {
        final ThirdPartyPercentageCache thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(0);
        final PercentageServiceImpl percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(20);
        thirdPartyPercentageCacheMock.setPercentage(75);
        Integer percentage = percentageService.getPercentage();
        assertEquals(20, percentage);
    }

}
