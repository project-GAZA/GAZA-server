package io.junrock.GAZA.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum HttpStatusCode {
    CREATED(201),
    OK(200),

    NOT_ENOUGH_MESSAGE_LENGTH(400),
    CONTAIN_BADWORD(451),
    NOT_FOUND_MESSAGE(406);

    private final int status;
}
