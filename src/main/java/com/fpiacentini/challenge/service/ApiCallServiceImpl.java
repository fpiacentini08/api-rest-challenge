package com.fpiacentini.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpiacentini.challenge.entity.ApiCall;
import com.fpiacentini.challenge.model.ApiCallModel;
import com.fpiacentini.challenge.model.CustomPage;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.repository.ApiCallPagingAndSortingRepository;
import com.fpiacentini.challenge.repository.ApiCallRepository;
import com.fpiacentini.challenge.transformer.ApiCallEntityToApiCallModelTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ApiCallServiceImpl implements ApiCallService {

    private final ObjectMapper mapper;
    private final ApiCallRepository apiCallRepository;
    private final ApiCallPagingAndSortingRepository apiCallPagingAndSortingRepository;

    @Autowired
    public ApiCallServiceImpl(ApiCallRepository apiCallRepository, ApiCallPagingAndSortingRepository apiCallPagingAndSortingRepository, ObjectMapper mapper) {
        this.apiCallRepository = apiCallRepository;
        this.apiCallPagingAndSortingRepository = apiCallPagingAndSortingRepository;
        this.mapper = mapper;
    }


    @Async("threadPoolTaskExecutor")
    public void createApiCallHistory(NumbersToAdd numbersToAdd, Result result) {
        try {
            createApiCallHistory(convertApiDataToString(numbersToAdd, result));
        } catch (JsonProcessingException e) {
            System.out.println(String.format("Entity could not be saved: %s, %s", numbersToAdd.toString(), result.toString()));
        }
        System.out.println(String.format("Entity saved: %s, %s", numbersToAdd.toString(), result.toString()));

    }

    private void createApiCallHistory(String apiCallData) {
        apiCallRepository.save(ApiCall.create(apiCallData));
    }

    private String convertApiDataToString(NumbersToAdd numbersToAdd, Result result) throws JsonProcessingException {
        var numbersToAddJson = mapper.writeValueAsString(numbersToAdd);
        var resultJson = mapper.writeValueAsString(result);
        return String.format("{\"request\":%s,\"response\":%s}", numbersToAddJson, resultJson);
    }

    @Override
    public CustomPage<ApiCallModel> getApiCallHistory(Integer pageNumber, Integer pageSize) {
        var sort = Sort.by(Sort.Direction.ASC, "createdAt");
        var pageable = PageRequest.of(pageNumber, pageSize, sort);
        var apiCallPage = apiCallPagingAndSortingRepository.findAll(pageable);
        return new CustomPage<>(ApiCallEntityToApiCallModelTransformer.transform(apiCallPage.getContent()), apiCallPage.getTotalElements(), apiCallPage.getNumber(), apiCallPage.getSize());
    }


}
