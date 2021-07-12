package com.example.greengrocerystore.external.common.accesskey;

import com.example.greengrocerystore.external.common.accesskey.service.GetVegetableAccessTokenService;
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
public class VegetableAccessToken {

    private String accessToken;

    private final GetVegetableAccessTokenService getVegetableAccessTokenService;

    @PostConstruct
    private void postConstruct() {
        refresh();
    }

    public void refresh() {
        getVegetableAccessTokenService.get()
            .subscribe(cookieString -> {
                List<HttpCookie> cookies = HttpCookie.parse(Objects.requireNonNull(cookieString));
                accessToken = cookies.get(0).getValue();
            });
    }
}
