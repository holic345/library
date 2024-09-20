package com.example.library.global.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <h4> 상태코드 400 enum 클래스 </h4>
 */
@Getter
@AllArgsConstructor
public enum StatusCodeBadRequestVO implements IStatusCode {

    // 공통
    BAD_REQUEST_40000000(HttpStatus.BAD_REQUEST, "40000000", "잘못된 요청 헤더 입니다."),
    BAD_REQUEST_40000001(HttpStatus.BAD_REQUEST, "40000001", "잘못된 요청 파라미터 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
