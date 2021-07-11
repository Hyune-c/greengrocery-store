package com.example.greengrocerystore.external.common.accesskey.service;

import com.example.greengrocerystore.external.common.accesskey.dto.FruitAccessKeyExternalDto;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetFruitAccessKeyService {

    private final String fruitBaseUrl;

    private final WebClient webClient;

    public Mono<FruitAccessKeyExternalDto> get() {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(fruitBaseUrl + "/token")
            .build();

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(FruitAccessKeyExternalDto.class);
    }
}
