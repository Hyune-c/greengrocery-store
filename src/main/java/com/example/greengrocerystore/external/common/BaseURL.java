package com.example.greengrocerystore.external.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseURL {

    @Value("${application.fruitBaseUrl}")
    private String fruitBaseUrl;

    @Value("${application.vegetableBaseUrl}")
    private String vegetableBaseUrl;

    @Bean
    public String fruitBaseUrl() {
        return fruitBaseUrl;
    }

    @Bean
    public String vegetableBaseUrl() {
        return vegetableBaseUrl;
    }
}
