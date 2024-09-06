package com.example.library.global.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <h4> 상태코드 400 enum 클래스 </h4>
 */
@Getter
@AllArgsConstructor
public enum StatusCodeInternalServerErrorVO implements IStatusCode {

    // 공통
    INTERNAL_SERVER_ERROR_50000000(HttpStatus.INTERNAL_SERVER_ERROR, "50000000", "Internal server error");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
