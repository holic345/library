package com.example.library.global.exception.list;

import com.example.library.global.exception.status.IStatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomBadRequestException extends RuntimeException {

    private IStatusCode errorCode;

    public CustomBadRequestException(IStatusCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomBadRequestException(IStatusCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
