package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.custom.CustomVegetableExceptionHelper;
import com.example.greengrocerystore.external.common.accesskey.VegetableAccessToken;
import com.example.greengrocerystore.external.dto.GetVegetableDto;
import com.example.greengrocerystore.external.dto.GetVegetableExternalDto;
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
public class GetVegetableService {

    private final VegetableAccessToken vegetableAccessToken;
    private final String vegetableBaseUrl;

    private final WebClient webClient;

    public Mono<GetVegetableDto> get(String name) {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(vegetableBaseUrl + "/item")
            .queryParam("name", name)
            .build();

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, vegetableAccessToken.getAccessToken())
            .retrieve()
            .bodyToMono(GetVegetableExternalDto.class)
            .doOnError(CustomVegetableExceptionHelper::of)
            .map(GetVegetableDto::new);
    }

    public Mono<List<GetVegetableDto>> get(List<String> names) {
        return Flux.fromIterable(names)
            .flatMap(this::get)
            .collectList();
    }
}
