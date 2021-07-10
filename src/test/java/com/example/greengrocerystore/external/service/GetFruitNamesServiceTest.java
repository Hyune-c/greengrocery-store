package com.example.greengrocerystore.external.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[service] 과일 이름 목록")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GetFruitNamesServiceTest {

    @Autowired
    private GetFruitNamesService getFruitNamesService;

    @DisplayName("[성공]")
    @Test
    public void get() {
        // given

        // when
        List<String> fruitNames = getFruitNamesService.get().block();

        // then
        assertThat(fruitNames).isNotNull();
        assertThat(fruitNames.size()).isGreaterThanOrEqualTo(0);
    }
}
