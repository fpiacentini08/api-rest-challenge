package com.fpiacentini.challenge.service;

import com.fpiacentini.challenge.entity.ApiCall;
import com.fpiacentini.challenge.repository.ApiCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiCallServiceImpl implements ApiCallService {

    private final ApiCallRepository apiCallRepository;

    @Autowired
    public ApiCallServiceImpl(ApiCallRepository apiCallRepository) {
        this.apiCallRepository = apiCallRepository;
    }

    @Override
    public void createApiCallHistory(String apiCallData) {
        apiCallRepository.save(ApiCall.create(apiCallData));
    }
}
