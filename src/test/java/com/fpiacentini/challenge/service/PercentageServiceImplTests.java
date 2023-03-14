package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.cache.ThirdPartyPercentageCache;
import com.fpiacentini.challenge.exception.NoPercentageAvailableException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class PercentageServiceImplTests {
    private final ThirdPartyPercentageServiceImpl thirdPartyPercentageServiceMock = mock(ThirdPartyPercentageServiceImpl.class);

    @Test
    void givenThirdPartyServiceResponse_whenGetPercentage_shouldReturnTheSameNumber() throws Throwable {
        final var thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(1800);
        final var percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(Optional.of(20));
        var percentage = percentageService.getPercentage();
        assertEquals(20, percentage);
        verify(thirdPartyPercentageServiceMock, times(1)).getPercentage();
    }

    @Test
    void givenCachedValueNotExpired_whenGetPercentage_shouldReturnTheCachedNumber() throws Throwable {
        final var thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(1800);
        final var percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(Optional.of(20));
        thirdPartyPercentageCacheMock.setPercentage(75);
        var percentage = percentageService.getPercentage();
        assertEquals(75, percentage);
        verify(thirdPartyPercentageServiceMock, times(0)).getPercentage();
    }

    @Test
    void givenCachedValueExpired_whenGetPercentage_shouldReturnTheThirdPartyServiceReturnedNumber() throws Throwable {
        final var thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(0);
        final var percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(Optional.of(20));
        thirdPartyPercentageCacheMock.setPercentage(75);
        var percentage = percentageService.getPercentage();
        assertEquals(20, percentage);
        verify(thirdPartyPercentageServiceMock, times(1)).getPercentage();
    }

    @Test
    void givenCacheEmptyAndNoResponseFromThirdPartyService_whenGetPercentage_shouldThrowNoPercentageAvailableException() throws Throwable {
        final var thirdPartyPercentageCacheMock = new ThirdPartyPercentageCache(0);
        final var percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock, thirdPartyPercentageCacheMock);
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(Optional.ofNullable(null));
        Assert.assertThrows(NoPercentageAvailableException.class , ()-> percentageService.getPercentage());

    }

}
