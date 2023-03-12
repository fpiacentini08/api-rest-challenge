package com.fpiacentini.challenge.client;

import com.fpiacentini.challenge.model.ThirdPartyServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ThirdPartyPercentageServiceHttpClient extends BaseHttpClient {

    public ThirdPartyPercentageServiceHttpClient(@Value("${third.party.http.client.timeout.millis.connection:2000}") Integer connectionTimeout,
                                                 @Value("${third.party.http.client.timeout.millis.read:2000}") Integer readTimeout,
                                                 @Value("${third.party.http.client.timeout.millis.write:2000}") Integer writeTimeout,
                                                 @Value("${third.party.http.client.timeout.millis.response:2000}") Integer responseTimeout,
                                                 @Value("${third.party.http.client.url:http://localhost:8081}") String url) {
        super(connectionTimeout, readTimeout, writeTimeout, responseTimeout, url);
    }

    public Integer getPercentage() {
        WebClient.RequestHeadersUriSpec<?> headersSpec = webClient.get();

        Mono<ThirdPartyServiceResponse> thirdPartyServiceResponseMono = headersSpec.exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(ThirdPartyServiceResponse.class);
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
        return thirdPartyServiceResponseMono.block().value();


    }
}
