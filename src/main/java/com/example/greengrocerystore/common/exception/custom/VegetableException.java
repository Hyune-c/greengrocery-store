package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class VegetableException extends RuntimeException {

    private final ErrorCode errorCode;

    public VegetableException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }
}
