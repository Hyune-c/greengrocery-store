package com.example.greengrocerystore.web.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.greengrocerystore.web.api.response.FruitResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("[web] 과일")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FruitControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("[성공] 과일 목록 조회")
    @Test
    public void getFruitV2() {
        // given

        // when
        List<FruitResponse> responses = webTestClient
            .get().uri("/api/v2/fruits")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(FruitResponse.class)
            .returnResult()
            .getResponseBody();

        // then
        assertThat(responses).isNotNull();
        assertThat(responses.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("[실패] 과일 목록 조회 - 존재하지 않는 과일 이름")
    @Test
    public void getFruitV2_notExistsFruit() {
        // given

        // when
        webTestClient
            .get().uri(uriBuilder -> uriBuilder.path("/api/v2/fruits")
            .queryParam("names", "외국채소")
            .build())
            .exchange()
            .expectStatus().isNotFound();

        // then
    }
}
