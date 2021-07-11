package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.ErrorCode;
import com.example.greengrocerystore.common.exception.custom.BusinessException;
import com.example.greengrocerystore.external.common.accesskey.VegetableAccessKey;
import com.example.greengrocerystore.external.dto.GetVegetableDto;
import com.example.greengrocerystore.external.dto.GetVegetableExternalDto;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetVegetableService {

    private final VegetableAccessKey vegetableAccessKey;
    private final String vegetableBaseUrl;

    private final WebClient webClient;

    public Mono<GetVegetableDto> get(String name) {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(vegetableBaseUrl + "/item")
            .queryParam("name", name)
            .build();

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, vegetableAccessKey.getAccessKey())
            .retrieve()
            .bodyToMono(GetVegetableExternalDto.class)
            .doOnError(throwable -> {
                if (throwable instanceof WebClientResponseException) {
                    WebClientResponseException webClientResponseException = (WebClientResponseException) throwable;

                    if (webClientResponseException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new BusinessException(ErrorCode.VEGETABLE_NOT_FOUND);
                    }

                    if (webClientResponseException.getResponseBodyAsString().contains("Access token required")) {
                        vegetableAccessKey.refreshVegetableAccessKey();
                        throw new BusinessException(ErrorCode.REFRESH_ACCESS_TOKEN);
                    }
                }
            })
            .map(GetVegetableDto::new);
    }

    public Mono<List<GetVegetableDto>> get(List<String> names) {
        return Flux.fromIterable(names)
            .flatMap(this::get)
            .doOnError(throwable -> {
                

                if (throwable.getMessage().contains("Not Found")) {
                    throw new BusinessException(ErrorCode.VEGETABLE_NOT_FOUND);
                }
            })
            .collectList();
    }
}
