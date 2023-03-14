package com.fpiacentini.challenge.client;

import com.fpiacentini.challenge.exception.NoResponseFromThirdPartyService;
import com.fpiacentini.challenge.model.ThirdPartyServiceResponse;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ThirdPartyPercentageServiceHttpClient extends BaseHttpClient {

    public ThirdPartyPercentageServiceHttpClient(@Value("${third.party.http.client.timeout.millis.connection:2000}") Integer connectionTimeout,
                                                 @Value("${third.party.http.client.timeout.millis.read:2000}") Integer readTimeout,
                                                 @Value("${third.party.http.client.timeout.millis.write:2000}") Integer writeTimeout,
                                                 @Value("${third.party.http.client.timeout.millis.response:2000}") Integer responseTimeout,
                                                 @Value("${third.party.http.client.retry.backoff.millis.interval:200}") Integer retryExponentialBackoffInterval,
                                                 @Value("${third.party.http.client.retry.backoff.multiplier:2}") Integer retryExponentialBackoffMultiplier,
                                                 @Value("${third.party.http.client.retry.backoff.max.attempts:3}") Integer retryExponentialBackoffIntervalMaxAttempts,
                                                 @Value("${third.party.http.client.url}") String url) {
        super(connectionTimeout, readTimeout, writeTimeout, responseTimeout, retryExponentialBackoffInterval, retryExponentialBackoffMultiplier, retryExponentialBackoffIntervalMaxAttempts, url);
    }

    public Integer getPercentage() throws Throwable {
        var thirdPartyServiceResponseMono = buildGetPercentageMono();
        var thirdPartyApiCallFunction = Retry
                .decorateCheckedFunction(retry, response -> thirdPartyServiceResponseMono.block().value());
        var response = thirdPartyApiCallFunction.apply(null);
        return response;
    }

    private Mono<ThirdPartyServiceResponse> buildGetPercentageMono() {
        return webClient.get().exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(ThirdPartyServiceResponse.class);
            } else {
                return Mono.error(new NoResponseFromThirdPartyService());
            }
        });
    }
}
