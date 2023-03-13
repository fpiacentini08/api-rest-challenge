package com.fpiacentini.challenge.client;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdPartyPercentageServiceHttpClientTests {

    private static final Integer TIMEOUT = 99999;
    private static MockWebServer mockWebServer;
    private ThirdPartyPercentageServiceHttpClient thirdPartyPercentageServiceHttpClient = new ThirdPartyPercentageServiceHttpClient(TIMEOUT, TIMEOUT, TIMEOUT, TIMEOUT, "http://localhost:9999");

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
    void givenResponseFromThirdPartyService20_whenGetPercentage_shouldReturn20() throws IOException, InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody("{\"value\":20}"));
        Integer percentage = thirdPartyPercentageServiceHttpClient.getPercentage();
        assertEquals(20, percentage);
    }
}
