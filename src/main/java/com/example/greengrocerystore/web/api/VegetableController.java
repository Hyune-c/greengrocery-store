package com.example.greengrocerystore.web.api;

import com.example.greengrocerystore.external.service.GetVegetableNamesService;
import com.example.greengrocerystore.external.service.GetVegetableService;
import com.example.greengrocerystore.web.api.response.GetVegetableNamesResponse;
import com.example.greengrocerystore.web.api.response.VegetableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class VegetableController {

    private final GetVegetableNamesService getVegetableNamesService;
    private final GetVegetableService getVegetableService;

    @GetMapping("/api/v1/vegetables/name")
    public Mono<GetVegetableNamesResponse> getVegetableNames() {
        return getVegetableNamesService.get()
            .map(GetVegetableNamesResponse::new);
    }

    @GetMapping("/api/v1/vegetables")
    public Mono<VegetableResponse> getVegetable(
        @RequestParam(required = false) String name) {
        return getVegetableService.get(name)
            .map(VegetableResponse::new);
    }
}
