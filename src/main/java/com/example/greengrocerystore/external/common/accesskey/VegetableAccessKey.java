package com.example.greengrocerystore.external.common.accesskey;

import com.example.greengrocerystore.external.common.accesskey.service.GetVegetableAccessKeyService;
import java.net.HttpCookie;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@RequiredArgsConstructor
@Configuration
public class VegetableAccessKey {

    private String accessKey;

    private final GetVegetableAccessKeyService getVegetableAccessKeyService;

    @PostConstruct
    private void postConstruct() {
        refreshVegetableAccessKey();
        log.info("### vegetableAccessKey={}", accessKey);
    }

    public void refreshVegetableAccessKey() {
        getVegetableAccessKeyService.get()
            .subscribe(cookieString -> {
                List<HttpCookie> cookies = HttpCookie.parse(Objects.requireNonNull(cookieString));
                accessKey = cookies.get(0).getValue();
            });
    }
}
