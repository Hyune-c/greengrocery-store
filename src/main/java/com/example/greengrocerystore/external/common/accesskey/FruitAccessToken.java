package com.example.greengrocerystore.external.common.accesskey;

import com.example.greengrocerystore.external.common.accesskey.service.GetFruitAccessTokenService;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@RequiredArgsConstructor
@Configuration
public class FruitAccessToken {

    private String accessToken;

    private final GetFruitAccessTokenService getFruitAccessTokenService;

    @PostConstruct
    private void postConstruct() {
        refresh();
    }

    public void refresh() {
        getFruitAccessTokenService.get()
            .subscribe(externalDto -> accessToken = externalDto.getAccessToken());
    }
}
