package com.example.greengrocerystore.external.service;

import com.example.greengrocerystore.common.exception.ErrorCode;
import com.example.greengrocerystore.common.exception.custom.BusinessException;
import com.example.greengrocerystore.external.common.accesskey.FruitAccessKey;
import com.example.greengrocerystore.external.dto.GetFruitDto;
import com.example.greengrocerystore.external.dto.GetFruitExternalDto;
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

    public Mono<List<GetFruitDto>> get(List<String> names) {
        return Flux.fromIterable(names)
            .flatMap(this::get)
            .doOnError(throwable -> {
                log.error("### external api call error. message={}. cause={}", throwable.getMessage(), throwable.getCause());

                if (throwable.getMessage().contains("Not Found")) {
                    throw new BusinessException(ErrorCode.FRUIT_NOT_FOUND);
                }
            })
            .collectList();
    }
}
