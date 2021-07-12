package com.example.greengrocerystore.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 청과물
    FRUIT_NOT_FOUND("E0100", HttpStatus.NOT_FOUND, "찾을 수 없는 과일"),
    VEGETABLE_NOT_FOUND("E0200", HttpStatus.NOT_FOUND, "찾을 수 없는 채소"),

    // 외부 API
    REFRESH_ACCESS_TOKEN("E0300", HttpStatus.CONFLICT, "accessToken 갱신"),

    // 기타
    BAD_REQUEST("E0400", HttpStatus.BAD_REQUEST, "잘못된 입력 값"),
    NOT_VALID_REQUEST_BODY("E0400", HttpStatus.BAD_REQUEST, "존재하지 않거나 읽을 수 없는 Request Body"),
    UNAUTHORIZED("E0401", HttpStatus.UNAUTHORIZED, "인증 실패"),
    FORBIDDEN("E0403", HttpStatus.FORBIDDEN, "권한 없음"),
    NOT_FOUND("E0404", HttpStatus.NOT_FOUND, "찾을 수 없음"),
    METHOD_NOT_ALLOWED("E0405", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드"),
    UNKNOWN("E0500", HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 에러");

    private final String code;
    private final HttpStatus status;
    private final String reason;
}
