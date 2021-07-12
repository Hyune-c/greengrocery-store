package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;

public class CustomVegetableAccessTokenException extends FruitException {

    public CustomVegetableAccessTokenException() {
        super(ErrorCode.REFRESH_ACCESS_TOKEN);
    }
}
