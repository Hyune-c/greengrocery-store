package com.example.greengrocerystore.web.api;

import com.example.greengrocerystore.common.type.GreenGroceryType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommonController {

    @GetMapping("/api/v1/types")
    public GreenGroceryType[] getTypes() {
        return GreenGroceryType.values();
    }
}
