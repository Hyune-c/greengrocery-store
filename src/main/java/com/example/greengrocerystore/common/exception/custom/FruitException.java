package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class FruitException extends RuntimeException {

    private final ErrorCode errorCode;

    public FruitException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }
}
