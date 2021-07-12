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

@DisplayName("[web] 채소")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class VegetableControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private final String v1BaseUrl = "/api/v1/vegetables";
    private final String v2BaseUrl = "/api/v2/vegetables";

    @DisplayName("[성공] v1 채소 조회")
    @Test
    public void getFruitV1() {
        // given

        // when
        List<FruitResponse> responses = webTestClient
            .get().uri(uriBuilder -> uriBuilder.path(v1BaseUrl)
                .queryParam("name", "치커리")
                .build())
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(FruitResponse.class)
            .returnResult()
            .getResponseBody();

        // then
        assertThat(responses).isNotNull();
        assertThat(responses.size()).isGreaterThanOrEqualTo(1);
    }

    @DisplayName("[실패] v1 채소 조회 - queryParam 누락")
    @Test
    public void getFruitV1_withoutQueryParam() {
        // given

        // when
        webTestClient
            .get().uri(v1BaseUrl)
            .exchange()
            .expectStatus().isBadRequest();

        // then

    }

    @DisplayName("[실패] v1 채소 조회 - 존재하지 않는 과일 이름")
    @Test
    public void getFruitV1_notExistsFruit() {
        // given

        // when
        webTestClient
            .get().uri(uriBuilder -> uriBuilder.path(v1BaseUrl)
            .queryParam("name", "외국과일")
            .build())
            .exchange()
            .expectStatus().isNotFound();

        // then

    }

    @DisplayName("[성공] v2 채소 목록 조회")
    @Test
    public void getFruitV2() {
        // given

        // when
        List<FruitResponse> responses = webTestClient
            .get().uri(v2BaseUrl)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(FruitResponse.class)
            .returnResult()
            .getResponseBody();

        // then
        assertThat(responses).isNotNull();
        assertThat(responses.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("[실패] v2 채소 목록 조회 - 존재하지 않는 채소 이름")
    @Test
    public void getFruitV2_notExistsFruit() {
        // given

        // when
        webTestClient
            .get().uri(uriBuilder -> uriBuilder.path(v2BaseUrl)
            .queryParam("names", "외국과일")
            .build())
            .exchange()
            .expectStatus().isNotFound();

        // then
    }
}
