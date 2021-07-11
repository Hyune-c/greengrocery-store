package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.ErrorCode;
import com.example.greengrocerystore.common.exception.custom.BusinessException;
import com.example.greengrocerystore.external.common.accesskey.VegetableAccessKey;
import com.example.greengrocerystore.external.dto.GetVegetableDto;
import com.example.greengrocerystore.external.dto.GetVegetableExternalDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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

    public Mono<List<GetVegetableDto>> get(List<String> names) {
        return Flux.fromIterable(names)
            .flatMap(this::get)
            .doOnError(throwable -> {
                log.error("### external api call error. message={}. cause={}", throwable.getMessage(), throwable.getCause());

                if (throwable.getMessage().contains("Not Found")) {
                    throw new BusinessException(ErrorCode.VEGETABLE_NOT_FOUND);
                }
            })
            .collectList();
    }
}
