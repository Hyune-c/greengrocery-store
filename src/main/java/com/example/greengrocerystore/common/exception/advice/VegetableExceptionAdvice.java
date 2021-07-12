package com.example.greengrocerystore.common.exception.advice;

import com.example.greengrocerystore.common.exception.ErrorResponse;
import com.example.greengrocerystore.common.exception.custom.CustomVegetableAccessTokenException;
import com.example.greengrocerystore.common.exception.custom.CustomVegetableException;
import com.example.greengrocerystore.external.common.accesskey.VegetableAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@RestControllerAdvice
public class VegetableExceptionAdvice extends BaseExceptionAdvice {

    private final VegetableAccessToken vegetableAccessToken;

    @ExceptionHandler(CustomVegetableAccessTokenException.class)
    protected ResponseEntity<ErrorResponse> handleCustomVegetableAccessTokenException(CustomVegetableAccessTokenException ex) {
        vegetableAccessToken.refresh();
        preHandle(ex);

        return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), ex.getErrorCode().getStatus());
    }

    @ExceptionHandler(CustomVegetableException.class)
    protected ResponseEntity<ErrorResponse> handleCustomVegetableException(CustomVegetableException ex) {
        preHandle(ex);

        return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), ex.getErrorCode().getStatus());
    }
}
