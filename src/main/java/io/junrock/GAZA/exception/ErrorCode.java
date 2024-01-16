package io.junrock.GAZA.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_ENOUGH_MESSAGE_LENGTH(HttpStatusCode.BAD_REQUEST.getStatus(),"메시지 길이가 너무 짧습니다!"),
    CONTAIN_BADWORD(HttpStatusCode.CONTAIN_BADWORD.getStatus(),"메시지에 욕설이 포함되어 있습니다!"),
    NOT_FOUND_MESSAGE(HttpStatusCode.NOT_FOUND_MESSAGE.getStatus(),"존재하지 않는 메시지입니다!"),
    EXIST_IP(HttpStatusCode.BAD_REQUEST.getStatus(),"이미 버튼을 누른 IP입니다!"),
    NOT_FOUND_DONATE(HttpStatusCode.NOT_FOUND_MESSAGE.getStatus(),"존재하지 않는 기부금입니다!"),
    NOt_FOUND_ADMIN(HttpStatusCode.BAD_REQUEST.getStatus(), "존재하지 않는 관리자입니다!"),
    NOT_EXIST_AUTHENTICATION(HttpStatusCode.UNAUTHORIZED.getStatus(), "Security Context에 인증 정보가 없습니다."),
    EXIST_ADMIN(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 존재하는 관리자입니다");
    private final int status;
    private final String message;
}
