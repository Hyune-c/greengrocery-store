package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.custom.CustomFruitExceptionHelper;
import com.example.greengrocerystore.external.common.accesskey.FruitAccessToken;
import com.google.gson.Gson;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetFruitNamesService {

    private final FruitAccessToken fruitAccessToken;
    private final String fruitBaseUrl;

    private final WebClient webClient;

    public Mono<List<String>> get() {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(fruitBaseUrl + "/product")
            .build();

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, fruitAccessToken.getAccessToken())
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(CustomFruitExceptionHelper::of)
            .map(names -> new Gson().fromJson(names, List.class));
    }
}
