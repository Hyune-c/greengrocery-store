package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.external.common.accesskey.VegetableAccessKey;
import com.example.greengrocerystore.external.dto.GetVegetableDto;
import com.example.greengrocerystore.external.dto.GetVegetableExternalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetVegetableService {

    private final VegetableAccessKey vegetableAccessKey;

    private final WebClient webClient;

    public Mono<GetVegetableDto> get(String name) {
        String uri = "http://vegetable.api.postype.net/item?name=" + name;

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, vegetableAccessKey.getAccessKey())
            .retrieve()
            .bodyToMono(GetVegetableExternalDto.class)
            .map(GetVegetableDto::new);
    }
}
