package com.example.greengrocerystore.web.api.response;

import com.example.greengrocerystore.external.dto.GetFruitDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FruitResponse {

    private String name;
    private Long price;

    public FruitResponse(GetFruitDto dto) {
        name = dto.getName();
        price = dto.getPrice();
    }
}
