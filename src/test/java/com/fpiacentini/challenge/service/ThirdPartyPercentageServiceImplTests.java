package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.client.ThirdPartyPercentageServiceHttpClient;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
