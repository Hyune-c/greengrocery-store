package com.example.greengrocerystore.external.common.accesskey;

import com.example.greengrocerystore.external.common.accesskey.service.GetFruitAccessKeyService;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@RequiredArgsConstructor
@Configuration
public class FruitAccessKey {

    private String accessKey;

    private final GetFruitAccessKeyService getFruitAccessKeyService;

    @PostConstruct
    private void postConstruct() {
        refreshFruitAccessKey();
        log.info("### fruitAccessKey={}", accessKey);
    }

    public void refreshFruitAccessKey() {
        getFruitAccessKeyService.get()
            .subscribe(externalDto -> accessKey = externalDto.getAccessToken());
    }
}
