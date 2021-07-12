package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;

public class CustomFruitException extends FruitException {

    public CustomFruitException(ErrorCode errorCode) {
        super(errorCode);
    }
}
