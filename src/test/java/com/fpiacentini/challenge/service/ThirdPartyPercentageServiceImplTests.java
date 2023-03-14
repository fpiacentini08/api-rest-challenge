package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.client.ThirdPartyPercentageServiceHttpClient;
import com.fpiacentini.challenge.exception.NoResponseFromThirdPartyServiceException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThirdPartyPercentageServiceImplTests {

    private final ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClientMock = mock(ThirdPartyPercentageServiceHttpClient.class);
    private final ThirdPartyPercentageServiceImpl thirdPartyPercentageService = new ThirdPartyPercentageServiceImpl(thirdPartyPercentageServiceHttpClientMock);

    @Test
    void givenExternalServiceResponse20_whenGetPercentage_shouldReturn20() throws Throwable {
        when(thirdPartyPercentageServiceHttpClientMock.getPercentage()).thenReturn(20);
        var percentage = thirdPartyPercentageService.getPercentage();
        assertEquals(Optional.of(20), percentage);
    }

    @Test
    void givenExternalServiceException_whenGetPercentage_shouldReturnEmptyOptional() throws Throwable {
        when(thirdPartyPercentageServiceHttpClientMock.getPercentage()).thenThrow(new NoResponseFromThirdPartyServiceException());
        var percentage = thirdPartyPercentageService.getPercentage();
        assertTrue(percentage.isEmpty());
    }
}
