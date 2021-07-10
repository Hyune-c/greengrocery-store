package com.example.greengrocerystore.external.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.greengrocerystore.external.dto.GetFruitDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[service] 과일 상세")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GetFruitServiceTest {

    @Autowired
    private GetFruitService getFruitService;

    @DisplayName("[성공]")
    @Test
    public void get() {
        // given

        // when
        GetFruitDto fruitDto = getFruitService.get("배").block();

        // then
        assertThat(fruitDto).isNotNull();
        assertThat(fruitDto.getName()).isNotBlank();
        assertThat(fruitDto.getPrice()).isGreaterThanOrEqualTo(0);
    }
}
