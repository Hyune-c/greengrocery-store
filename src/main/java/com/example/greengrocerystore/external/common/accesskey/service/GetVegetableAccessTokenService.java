package com.example.greengrocerystore.external.common.accesskey.service;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetVegetableAccessTokenService {

    private final String vegetableBaseUrl;

    private final WebClient webClient;

    public Mono<String> get() {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(vegetableBaseUrl + "/token")
            .build();

        return webClient.get()
            .uri(uri)
            .exchangeToMono(result -> {
                    String accessKey = result.headers().header(HttpHeaders.SET_COOKIE).get(0);
                    return Mono.just(accessKey);
                }
            );
    }
}
