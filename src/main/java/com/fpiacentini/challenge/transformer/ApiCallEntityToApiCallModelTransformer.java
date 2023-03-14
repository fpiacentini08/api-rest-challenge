package com.fpiacentini.challenge.transformer;

import com.fpiacentini.challenge.entity.ApiCall;
import com.fpiacentini.challenge.model.ApiCallModel;

import java.util.List;
import java.util.stream.Collectors;

public class ApiCallEntityToApiCallModelTransformer {

    public static List<ApiCallModel> transform(List<ApiCall> apiCallEntitiesList){
        return apiCallEntitiesList.stream().map(ApiCallEntityToApiCallModelTransformer::transform).collect(Collectors.toList());
    }

    public static ApiCallModel transform(ApiCall apicall){
        return new ApiCallModel(apicall.getId(), apicall.getData(), apicall.getCreatedAt());
    }

}
