package io.junrock.GAZA.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GazaException.class)
    protected ResponseEntity<ErrorResponseEntity> errorCodeResponseEntity(GazaException ex){
        return ErrorResponseEntity.responseEntity(ex.getErrorCode());
    }
}
