package com.example.greengrocerystore.web.api.response;

import com.example.greengrocerystore.external.dto.GetVegetableDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VegetableResponse {

    private String name;
    private Long price;

    public VegetableResponse(GetVegetableDto dto) {
        name = dto.getName();
        price = dto.getPrice();
    }
}
