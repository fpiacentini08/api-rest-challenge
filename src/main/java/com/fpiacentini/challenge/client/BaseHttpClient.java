package com.fpiacentini.challenge.client;

import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class BaseHttpClient {


    protected WebClient webClient;
    protected Retry retry;

    public BaseHttpClient(
            Integer connectionTimeout,
            Integer readTimeout,
            Integer writeTimeout,
            Integer responseTimeout,
            Integer retryExponentialBackoffInterval,
            Integer retryExponentialBackoffMultiplier,
            Integer retryExponentialBackoffIntervalMaxAttempts,
            String url) {
        webClient = buildWebClient(connectionTimeout, responseTimeout, readTimeout, writeTimeout, url);
        retry = buildRetryMechanism(retryExponentialBackoffInterval, retryExponentialBackoffMultiplier, retryExponentialBackoffIntervalMaxAttempts);
    }

    private WebClient buildWebClient(
            Integer connectionTimeout,
            Integer responseTimeout,
            Integer readTimeout,
            Integer writeTimeout,
            String url) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                .responseTimeout(Duration.ofMillis(responseTimeout))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .build();

    }

    private Retry buildRetryMechanism(Integer retryExponentialBackoffInterval, Integer retryExponentialBackoffMultiplier, Integer retryExponentialBackoffIntervalMaxAttempts) {
        IntervalFunction exponentialBackoffIntervalFunction =
                IntervalFunction.ofExponentialBackoff(retryExponentialBackoffInterval, retryExponentialBackoffMultiplier);

        RetryConfig retryConfiguration = RetryConfig.custom()
                .maxAttempts(retryExponentialBackoffIntervalMaxAttempts)
                .intervalFunction(exponentialBackoffIntervalFunction)
                .retryExceptions(IllegalStateException.class)
                .build();
        return Retry.of("getPercentage", retryConfiguration);
    }


}
