package io.junrock.GAZA.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageDonateDto{
    private Long messageId;
    private String username;
    private String content;

}
