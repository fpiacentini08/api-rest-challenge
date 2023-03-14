package com.fpiacentini.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpiacentini.challenge.entity.ApiCall;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.repository.ApiCallPagingAndSortingRepository;
import com.fpiacentini.challenge.repository.ApiCallRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApiCallServiceImplTests {

    private final ApiCallRepository apiCallRepository = mock(ApiCallRepository.class);
    private final ApiCallPagingAndSortingRepository apiCallPagingAndSortingRepository = mock(ApiCallPagingAndSortingRepository.class);
    private final ObjectMapper mapper = mock(ObjectMapper.class);
    private final ApiCallServiceImpl apiCallService = new ApiCallServiceImpl(apiCallRepository, apiCallPagingAndSortingRepository, mapper);

    @Test
    void givenNumbersToAddAndResult_whenCreateApiCallHistory_shouldExecuteRepositorySave() {
        when(apiCallRepository.save(any())).thenReturn(null);
        var numbersToAdd = new NumbersToAdd(1, 1);
        var result = new Result(2d);
        apiCallService.createApiCallHistory(numbersToAdd, result);
        verify(apiCallRepository, times(1)).save(any());
    }

    @Test
    void givenThrowingAJsonProcessingException_whenCreateApiCallHistory_shouldNotExecuteSave() throws JsonProcessingException {
        when(mapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("Error") {
        });
        var numbersToAdd = new NumbersToAdd(1, 1);
        var result = new Result(2d);
        apiCallService.createApiCallHistory(numbersToAdd, result);
        verify(apiCallRepository, times(0)).save(any());
    }

    @Test
    void givenPageSizeAndPageNumber_whenGetApiCallHistory_shouldReturnHistory() {
        var id = "123";
        var data = "sample-value";
        var createdAt = LocalDateTime.now();
        var apiCall = new ApiCall(id, data, createdAt);
        var pageable = PageRequest.of(0, 20);
        var apiCalls = Collections.singletonList(apiCall);
        var apiCallsPage = new PageImpl<>(apiCalls, pageable, 1);
        when(apiCallPagingAndSortingRepository.findAll(any(PageRequest.class))).thenReturn(apiCallsPage);
        var apiCallModelCustomPage = apiCallService.getApiCallHistory(0, 20);
        verify(apiCallPagingAndSortingRepository, times(1)).findAll(any(Pageable.class));
        assertEquals(0, apiCallModelCustomPage.pageNumber());
        assertEquals(20, apiCallModelCustomPage.pageSize());
        assertEquals(1, apiCallModelCustomPage.totalElements());
        var apiCallModel = apiCallModelCustomPage.content().get(0);
        assertEquals(id, apiCallModel.id());
        assertEquals(data, apiCallModel.data());
        assertEquals(createdAt, apiCallModel.createdAt());
    }


}
