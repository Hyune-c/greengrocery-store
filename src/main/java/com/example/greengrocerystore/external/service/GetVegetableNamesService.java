package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.custom.CustomVegetableExceptionHelper;
import com.example.greengrocerystore.external.common.accesskey.VegetableAccessToken;
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
public class GetVegetableNamesService {

    private final VegetableAccessToken vegetableAccessToken;
    private final String vegetableBaseUrl;

    private final WebClient webClient;

    public Mono<List<String>> get() {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(vegetableBaseUrl + "/item")
            .build();

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, vegetableAccessToken.getAccessToken())
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(CustomVegetableExceptionHelper::of)
            .map(names -> new Gson().fromJson(names, List.class));
    }
}
