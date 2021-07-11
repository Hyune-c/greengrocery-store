package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.ErrorCode;
import com.example.greengrocerystore.common.exception.custom.BusinessException;
import com.example.greengrocerystore.external.common.accesskey.FruitAccessToken;
import com.example.greengrocerystore.external.dto.GetFruitDto;
import com.example.greengrocerystore.external.dto.GetFruitExternalDto;
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
            .doOnError(throwable -> {
                if (throwable instanceof WebClientResponseException) {
                    WebClientResponseException webClientResponseException = (WebClientResponseException) throwable;

                    if (webClientResponseException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new BusinessException(ErrorCode.FRUIT_NOT_FOUND);
                    }

                    if (webClientResponseException.getResponseBodyAsString().contains("Access token required")) {
                        fruitAccessToken.refresh();
                        throw new BusinessException(ErrorCode.REFRESH_ACCESS_TOKEN);
                    }
                }
            })
            .map(GetFruitDto::new);
    }

    public Mono<List<GetFruitDto>> get(List<String> names) {
        return Flux.fromIterable(names)
            .flatMap(this::get)
            .doOnError(throwable -> {
                if (throwable.getMessage().contains("Not Found")) {
                    throw new BusinessException(ErrorCode.FRUIT_NOT_FOUND);
                }
            })
            .collectList();
    }
}
