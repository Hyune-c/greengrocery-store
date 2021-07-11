package com.example.greengrocerystore.external.common.accesskey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetVegetableAccessKeyService {

    private final WebClient webClient;

    public Mono<String> get() {
        String uri = "http://vegetable.api.postype.net/token";

        return webClient.get()
            .uri(uri)
            .exchangeToMono(result -> {
                    String accessKey = result.headers().header(HttpHeaders.SET_COOKIE).get(0);
                    return Mono.just(accessKey);
                }
            );
    }
}
