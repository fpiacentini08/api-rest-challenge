package com.fpiacentini.challenge.entity;


import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class ApiCall {

    @Id
    private final String id;

    private final String data;

    private final LocalDateTime createdAt;

    public ApiCall(String id, String data, LocalDateTime createdAt) {
        this.id = id;
        this.data = data;
        this.createdAt = createdAt;
    }

    public static ApiCall create(String apiCallData) {
        return new ApiCall(null, apiCallData, LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
