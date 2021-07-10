package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.external.common.accesskey.FruitAccessKey;
import com.example.greengrocerystore.external.dto.GetFruitDto;
import com.example.greengrocerystore.external.dto.GetFruitExternalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetFruitService {

    private final FruitAccessKey fruitAccessKey;

    private final WebClient webClient;

    public Mono<GetFruitDto> get(String name) {
        String uri = "http://fruit.api.postype.net/product?name=" + name;

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, fruitAccessKey.getAccessKey())
            .retrieve()
            .bodyToMono(GetFruitExternalDto.class)
            .map(GetFruitDto::new);
    }
}
