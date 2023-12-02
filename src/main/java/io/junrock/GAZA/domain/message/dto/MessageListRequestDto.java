package io.junrock.GAZA.domain.message.dto;

import lombok.Getter;

@Getter
public class MessageListRequestDto {
    String ip;
    PageRequestDto pageRequest;
}
