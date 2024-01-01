package io.junrock.GAZA.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GazaException.class)
    protected ResponseEntity errorCodeResponseEntity(GazaException ex){
        System.out.println(ex.getMessage());
        return ResponseEntity.ok(ex.getErrorCode().getStatus().value());
    }
}
