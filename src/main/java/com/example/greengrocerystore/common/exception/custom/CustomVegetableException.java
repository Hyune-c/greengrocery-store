package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;

public class CustomVegetableException extends FruitException {

    public CustomVegetableException(ErrorCode errorCode) {
        super(errorCode);
    }
}
