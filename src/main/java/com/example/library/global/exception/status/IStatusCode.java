package com.example.library.global.exception.status;

import org.springframework.http.HttpStatus;

/**
 * <h4> 상태코드 인터페이스 </h4>
 */
public interface IStatusCode {

    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();
}
