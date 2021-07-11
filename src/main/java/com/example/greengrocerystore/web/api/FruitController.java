package com.example.greengrocerystore.web.api;

import com.example.greengrocerystore.external.service.GetFruitNamesService;
import com.example.greengrocerystore.external.service.GetFruitService;
import com.example.greengrocerystore.web.api.response.FruitResponse;
import com.example.greengrocerystore.web.api.response.GetFruitNamesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class FruitController {

    private final GetFruitNamesService getFruitNamesService;
    private final GetFruitService getFruitService;

    @GetMapping("/api/v1/fruits/name")
    public Mono<GetFruitNamesResponse> getFruitNames() {
        return getFruitNamesService.get()
            .map(GetFruitNamesResponse::new);
    }

    @GetMapping("/api/v1/fruits")
    public Mono<FruitResponse> getFruit(
        @RequestParam(required = false) String name) {
        return getFruitService.get(name)
            .map(FruitResponse::new);
    }
}
