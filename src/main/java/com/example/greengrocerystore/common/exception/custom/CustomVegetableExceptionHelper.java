package com.example.greengrocerystore.common.exception.custom;

import com.example.greengrocerystore.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class CustomVegetableExceptionHelper {

    public static void of(Throwable throwable) {
        if (throwable instanceof WebClientResponseException) {
            WebClientResponseException webClientResponseException = (WebClientResponseException) throwable;

            if (webClientResponseException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new CustomVegetableException(ErrorCode.VEGETABLE_NOT_FOUND);
            }

            if (webClientResponseException.getResponseBodyAsString().contains("Access token required")) {
                throw new CustomVegetableAccessTokenException();
            }
        }

        throw new CustomVegetableException(ErrorCode.UNKNOWN);
    }
}
