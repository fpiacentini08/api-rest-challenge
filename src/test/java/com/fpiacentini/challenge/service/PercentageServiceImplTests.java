package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.cache.ThirdPartyPercentageCache;
import com.fpiacentini.challenge.mock.ThirdPartyPercentageServiceMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PercentageServiceImplTests {
    private final ThirdPartyPercentageServiceMock thirdPartyPercentageServiceMock = mock(ThirdPartyPercentageServiceMock.class);
    private final ThirdPartyPercentageCache thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache();
    private final PercentageServiceImpl percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);

    @Test
    void givenThirdPartyServiceResponse_whenGetPercentage_shouldReturnTheSameNumber() {
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(20);
        Integer percentage = percentageService.getPercentage();
        assertEquals(20, percentage);
    }

    @Test
    void givenCachedValueNotExpired_whenGetPercentage_shouldReturnTheCachedNumber() {
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(20);
        thirdPartyPercentageCacheMock.setPercentage(75);
        Integer percentage = percentageService.getPercentage();
        assertEquals(75, percentage);
    }
}
