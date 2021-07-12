package com.example.greengrocerystore.common.exception.advice;

import com.example.greengrocerystore.common.exception.ErrorResponse;
import com.example.greengrocerystore.common.exception.custom.CustomFruitAccessTokenException;
import com.example.greengrocerystore.common.exception.custom.CustomFruitException;
import com.example.greengrocerystore.external.common.accesskey.FruitAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@RestControllerAdvice
public class FruitExceptionAdvice extends BaseExceptionAdvice {

    private final FruitAccessToken fruitAccessToken;

    @ExceptionHandler(CustomFruitAccessTokenException.class)
    protected ResponseEntity<ErrorResponse> handleCustomFruitAccessTokenException(CustomFruitAccessTokenException ex) {
        fruitAccessToken.refresh();
        preHandle(ex);

        return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), ex.getErrorCode().getStatus());
    }

    @ExceptionHandler(CustomFruitException.class)
    protected ResponseEntity<ErrorResponse> handleCustomFruitException(CustomFruitException ex) {
        preHandle(ex);

        return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), ex.getErrorCode().getStatus());
    }
}
