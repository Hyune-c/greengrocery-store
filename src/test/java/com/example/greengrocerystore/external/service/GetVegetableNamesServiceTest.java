package com.example.greengrocerystore.external.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[service] 채소 이름 목록")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GetVegetableNamesServiceTest {

    @Autowired
    private GetVegetableNamesService getVegetableNamesService;

    @DisplayName("[성공]")
    @Test
    public void get() {
        // given

        // when
        List<String> vegetableNames = getVegetableNamesService.get().block();

        // then
        assertThat(vegetableNames).isNotNull();
        assertThat(vegetableNames.size()).isGreaterThanOrEqualTo(0);
    }
}
