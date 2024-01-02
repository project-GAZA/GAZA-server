package io.junrock.GAZA.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GazaException extends RuntimeException{
    ErrorCode errorCode;
}
