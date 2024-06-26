package io.junrock.GAZA.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum HttpStatusCode {
    CREATED(201),
    OK(200),

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    CONTAIN_BADWORD(451),
    NOT_FOUND_MESSAGE(406);

    private final int status;
}
