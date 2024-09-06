package com.example.library.global.exception.list;

import com.example.library.global.exception.status.IStatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomInternalServerErrorException extends RuntimeException {

    private IStatusCode errorCode;

    public CustomInternalServerErrorException(IStatusCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomInternalServerErrorException(IStatusCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
