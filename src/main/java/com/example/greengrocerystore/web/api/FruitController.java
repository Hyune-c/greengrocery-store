package com.example.greengrocerystore.web.api;

import com.example.greengrocerystore.external.service.GetFruitNamesService;
import com.example.greengrocerystore.external.service.GetFruitService;
import com.example.greengrocerystore.web.api.response.FruitResponse;
import com.example.greengrocerystore.web.api.response.GetFruitNamesResponse;
import java.util.List;
import java.util.stream.Collectors;
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
        @RequestParam String name) {
        return getFruitService.get(name)
            .map(FruitResponse::new);
    }

    @GetMapping("/api/v2/fruits")
    public Mono<List<FruitResponse>> getFruitV2(
        @RequestParam(required = false) List<String> names) {
        if (names == null) {
            names = getFruitNamesService.get().block();
        }

        return getFruitService.get(names)
            .map(getFruitDtos -> getFruitDtos.stream()
                .map(FruitResponse::new)
                .collect(Collectors.toList()));
    }
}
