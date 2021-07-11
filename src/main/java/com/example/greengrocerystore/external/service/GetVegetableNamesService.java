package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.ErrorCode;
import com.example.greengrocerystore.common.exception.custom.BusinessException;
import com.example.greengrocerystore.external.common.accesskey.VegetableAccessKey;
import com.google.gson.Gson;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetVegetableNamesService {

    private final VegetableAccessKey vegetableAccessKey;
    private final String vegetableBaseUrl;

    private final WebClient webClient;

    public Mono<List<String>> get() {
        URI uri = new DefaultUriBuilderFactory()
            .uriString(vegetableBaseUrl + "/item")
            .build();

        return webClient.get()
            .uri(uri)
            .header(HttpHeaders.AUTHORIZATION, vegetableAccessKey.getAccessKey())
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(throwable -> {
                if (throwable instanceof WebClientResponseException) {
                    WebClientResponseException webClientResponseException = (WebClientResponseException) throwable;

                    if (webClientResponseException.getResponseBodyAsString().contains("Access token required")) {
                        vegetableAccessKey.refreshVegetableAccessKey();
                        throw new BusinessException(ErrorCode.REFRESH_ACCESS_TOKEN);
                    }
                }
            })
            .map(names -> new Gson().fromJson(names, List.class));
    }
}
