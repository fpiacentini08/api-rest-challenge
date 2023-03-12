package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.client.ThirdPartyPercentageServiceHttpClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThirdPartyPercentageServiceImplTests {

    private ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClientMock = mock(ThirdPartyPercentageServiceHttpClient.class);
    private ThirdPartyPercentageServiceImpl thirdPartyPercentageService = new ThirdPartyPercentageServiceImpl(thirdPartyPercentageServiceHttpClientMock);

    @Test
    void givenExternalServiceResponse20_whenGetPercentage_shouldReturn20() {
        when(thirdPartyPercentageServiceHttpClientMock.getPercentage()).thenReturn(20);
        Integer percentage = thirdPartyPercentageService.getPercentage();
        assertEquals(20, percentage);
    }
}