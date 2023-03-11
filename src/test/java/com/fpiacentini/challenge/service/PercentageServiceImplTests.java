package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.mock.ThirdPartyPercentageServiceMock;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PercentageServiceImplTests {
    private final ThirdPartyPercentageServiceMock thirdPartyPercentageServiceMock = mock(ThirdPartyPercentageServiceMock.class);
    private final PercentageServiceImpl percentageService = new PercentageServiceImpl(thirdPartyPercentageServiceMock);

    @Test
    void givenThirdPartyServiceResponse_whenGetPercentage_shouldReturnTheSameNumber(){
        when(thirdPartyPercentageServiceMock.getPercentage()).thenReturn(20);
        Integer percentage = percentageService.getPercentage();
        assertEquals(20, percentage);
    }

}
