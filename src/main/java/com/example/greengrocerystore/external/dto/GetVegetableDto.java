package com.example.greengrocerystore.external.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetVegetableDto {

    private String name;
    private Long price;

    public GetVegetableDto(GetVegetableExternalDto externalDto) {
        this.name = externalDto.getName();
        this.price = externalDto.getPrice();
    }
}
