package com.example.greengrocerystore.web.api;

import com.example.greengrocerystore.external.service.GetVegetableNamesService;
import com.example.greengrocerystore.external.service.GetVegetableService;
import com.example.greengrocerystore.web.api.response.GetVegetableNamesResponse;
import com.example.greengrocerystore.web.api.response.VegetableResponse;
import java.util.List;
import java.util.stream.Collectors;
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

    @GetMapping("/api/v2/vegetables")
    public Mono<List<VegetableResponse>> getVegetableV2(
        @RequestParam(required = false) List<String> names) {
        if (names == null) {
            names = getVegetableNamesService.get().block();
        }

        return getVegetableService.get(names)
            .map(getVegetableDtos -> getVegetableDtos.stream()
                .map(VegetableResponse::new)
                .collect(Collectors.toList()));
    }
}
