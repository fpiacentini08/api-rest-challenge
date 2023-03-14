package com.fpiacentini.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fpiacentini.challenge.model.ApiCallModel;
import com.fpiacentini.challenge.model.CustomPage;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;

import java.util.List;

public interface ApiCallService {

    void createApiCallHistory(NumbersToAdd numbersToAdd, Result result) throws JsonProcessingException;
    CustomPage<ApiCallModel> getApiCallHistory();
}
