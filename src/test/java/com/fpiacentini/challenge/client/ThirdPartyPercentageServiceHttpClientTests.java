package com.fpiacentini.challenge.client;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdPartyPercentageServiceHttpClientTests {

    private static final Integer TIMEOUT = 2000;
    private static MockWebServer mockWebServer;
    private final ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient = new ThirdPartyPercentageServiceHttpClient(TIMEOUT, TIMEOUT, TIMEOUT, TIMEOUT, 1, 2, 3, "http://localhost:9999");

    @BeforeAll
    static void initialize() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(9999);
    }

    @AfterAll
    static void shutdown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void givenResponseFromThirdPartyService20_whenGetPercentage_shouldReturn20() throws Throwable {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{\"value\":20}"));
        var percentage = thirdPartyPercentageServiceHttpClient.getPercentage();
        assertEquals(20, percentage);
    }

    @Test
    void givenOneFailedResponseFromThirdPartyServiceAndThenGet20_whenGetPercentage_shouldReturn20() throws Throwable {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{}"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{\"value\":20}"));
        var percentage = thirdPartyPercentageServiceHttpClient.getPercentage();
        assertEquals(20, percentage);
    }

    @Test
    void givenThreeFailedResponsesFromThirdPartyServiceAndThenGet20_whenGetPercentage_shouldThrowException() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{}"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{}"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{}"));
        Assertions.assertThrows(IllegalStateException.class, () -> {
            thirdPartyPercentageServiceHttpClient.getPercentage();
        });
    }

}
