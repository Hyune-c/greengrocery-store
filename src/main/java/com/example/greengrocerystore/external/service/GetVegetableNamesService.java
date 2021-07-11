package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.external.common.accesskey.VegetableAccessKey;
import com.google.gson.Gson;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetVegetableNamesService {

    private final VegetableAccessKey vegetableAccessKey;

    private final WebClient webClient;

    public Mono<List<String>> get() {
        String uri = "http://vegetable.api.postype.net/item";

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, vegetableAccessKey.getAccessKey())
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(throwable ->
                log.error("### external api call error. message={}. cause={}", throwable.getMessage(), throwable.getCause()))
            .map(names -> new Gson().fromJson(names, List.class));
    }
}
