package com.fpiacentini.challenge.transformer;

import com.fpiacentini.challenge.entity.ApiCall;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiCallEntityToApiCallModelTransformerTests {

    @Test
    void givenApiCall_whenTransform_shouldReturnApiCallModel(){
        var id = "123";
        var data = "sample-value";
        var createdAt = LocalDateTime.now();

        var apiCallModel = ApiCallEntityToApiCallModelTransformer.transform(new ApiCall(id, data, createdAt));

        assertEquals(id, apiCallModel.id());
        assertEquals(data, apiCallModel.data());
        assertEquals(createdAt, apiCallModel.createdAt());
    }

    @Test
    void givenApiCallList_whenTransform_shouldReturnApiCallModelList(){
        var id = "123";
        var data = "sample-value";
        var createdAt = LocalDateTime.now();
        var apiCall = new ApiCall(id, data, createdAt);
        var id2 = "456";
        var data2 = "sample-value2";
        var createdAt2 = LocalDateTime.now();
        var apiCall2 = new ApiCall(id2, data2, createdAt2);

        var apiCallModelList = ApiCallEntityToApiCallModelTransformer.transform(Arrays.asList(apiCall, apiCall2));

        assertEquals(2, apiCallModelList.size());
        assertTrue(apiCallModelList.stream().anyMatch(apiCallModel -> apiCallModel.id().equals(id) && apiCallModel.data().equals(data) && apiCallModel.createdAt().equals(createdAt)));
        assertTrue(apiCallModelList.stream().anyMatch(apiCallModel -> apiCallModel.id().equals(id2) && apiCallModel.data().equals(data2) && apiCallModel.createdAt().equals(createdAt2)));
    }

    @Test
    void givenEmptyList_whenTransform_shouldReturnEmptyList(){
        var apiCallModelList = ApiCallEntityToApiCallModelTransformer.transform(new ArrayList<>());

        assertEquals(0, apiCallModelList.size());
    }

}
