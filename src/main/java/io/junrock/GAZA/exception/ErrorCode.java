package io.junrock.GAZA.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_ENOUGH_MESSAGE_LENGTH(HttpStatus.BAD_REQUEST,"메시지 길이가 너무 짧습니다!"),
    CONTAIN_BADWORD(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS,"메시지에 욕설이 포함되어 있습니다!");

    private final HttpStatus status;
    private final String message;
}
