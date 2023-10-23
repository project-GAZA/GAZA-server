package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.member.entity.Member;
import io.junrock.GAZA.domain.message.entity.Message;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageDto {
    private String content;

    public Message toEntity(Member member) {
        return Message.builder()
                .content(content)
                .member(member)
                .build();
    }
}
