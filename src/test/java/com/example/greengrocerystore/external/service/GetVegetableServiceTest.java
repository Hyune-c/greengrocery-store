package com.example.greengrocerystore.external.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.greengrocerystore.external.dto.GetVegetableDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("[service] 채소 상세")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GetVegetableServiceTest {

    @Autowired
    private GetVegetableService getVegetableService;

    @DisplayName("[성공]")
    @Test
    public void get() {
        // given

        // when
        GetVegetableDto vegetableDto = getVegetableService.get("치커리").block();

        // then
        assertThat(vegetableDto).isNotNull();
        assertThat(vegetableDto.getName()).isNotBlank();
        assertThat(vegetableDto.getPrice()).isGreaterThanOrEqualTo(0);
    }
}
