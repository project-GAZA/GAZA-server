package io.junrock.GAZA.exception;

import lombok.Getter;

@Getter
public class GazaException extends RuntimeException{
    private ErrorCode errorCode;

    public GazaException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
