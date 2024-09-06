package com.example.library.global.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <h4> 상태코드 200 enum 클래스 </h4>
 */
@Getter
@AllArgsConstructor
public enum StatusCodeOkVO implements IStatusCode {

    OK_20000000(HttpStatus.OK, "20000000", "정상적으로 처리되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
