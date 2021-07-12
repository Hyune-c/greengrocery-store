package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;

public class CustomFruitAccessTokenException extends FruitException {

    public CustomFruitAccessTokenException() {
        super(ErrorCode.REFRESH_ACCESS_TOKEN);
    }
}
