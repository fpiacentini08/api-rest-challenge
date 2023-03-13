package com.fpiacentini.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fpiacentini.challenge.model.NumbersToAdd;
import com.fpiacentini.challenge.model.Result;

public interface ApiCallService {

    void createApiCallHistory(NumbersToAdd numbersToAdd, Result result) throws JsonProcessingException;
}
