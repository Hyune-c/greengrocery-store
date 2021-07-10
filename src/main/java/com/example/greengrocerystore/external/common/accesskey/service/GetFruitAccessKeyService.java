package com.example.greengrocerystore.external.common.accesskey.service;

import com.example.greengrocerystore.external.common.accesskey.dto.FruitAccessKeyExternalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetFruitAccessKeyService {

    private final WebClient webClient;

    public Mono<FruitAccessKeyExternalDto> get() {
        String uri = "http://fruit.api.postype.net/token";

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(FruitAccessKeyExternalDto.class);
    }
}
