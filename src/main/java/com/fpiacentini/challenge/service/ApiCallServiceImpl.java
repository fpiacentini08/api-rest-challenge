package com.fpiacentini.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpiacentini.challenge.entity.ApiCall;
import com.fpiacentini.challenge.model.ApiCallModel;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;
import com.fpiacentini.challenge.repository.ApiCallPagingAndSortingRepository;
import com.fpiacentini.challenge.repository.ApiCallRepository;
import com.fpiacentini.challenge.transformer.ApiCallEntityToApiCallModelTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ApiCallServiceImpl implements ApiCallService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final ApiCallRepository apiCallRepository;
    private final ApiCallPagingAndSortingRepository apiCallPagingAndSortingRepository;

    @Autowired
    public ApiCallServiceImpl(ApiCallRepository apiCallRepository, ApiCallPagingAndSortingRepository apiCallPagingAndSortingRepository) {
        this.apiCallRepository = apiCallRepository;
        this.apiCallPagingAndSortingRepository = apiCallPagingAndSortingRepository;
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
        String numbersToAddJson = mapper.writeValueAsString(numbersToAdd);
        String resultJson = mapper.writeValueAsString(result);
        return String.format("{\"request\":%s,\"response\":%s}", numbersToAddJson, resultJson);
    }

    @Override
    public List<ApiCallModel> getApiCallHistory() {
        Iterator<ApiCall> apiCallEntitiesIterator = apiCallPagingAndSortingRepository.findAll().iterator();
        List<ApiCall> apiCallEntitiesList = new ArrayList<>();
        apiCallEntitiesIterator.forEachRemaining(apiCallEntitiesList::add);
        return ApiCallEntityToApiCallModelTransformer.transform(apiCallEntitiesList);
    }


}
