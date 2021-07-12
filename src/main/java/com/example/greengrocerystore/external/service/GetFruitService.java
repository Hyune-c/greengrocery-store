package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.custom.CustomFruitExceptionHelper;
import com.example.greengrocerystore.external.common.accesskey.FruitAccessToken;
import com.example.greengrocerystore.external.dto.GetFruitDto;
import com.example.greengrocerystore.external.dto.GetFruitExternalDto;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetFruitService {

    private final FruitAccessToken fruitAccessToken;
    private final String fruitBaseUrl;

    private final WebClient webClient;

    public Mono<GetFruitDto> get(String name) {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(fruitBaseUrl + "/product")
            .queryParam("name", name)
            .build();

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, fruitAccessToken.getAccessToken())
            .retrieve()
            .bodyToMono(GetFruitExternalDto.class)
            .doOnError(CustomFruitExceptionHelper::of)
            .map(GetFruitDto::new);
    }

    public Mono<List<GetFruitDto>> get(List<String> names) {
        return Flux.fromIterable(names)
            .flatMap(this::get)
            .collectList();
    }
}
