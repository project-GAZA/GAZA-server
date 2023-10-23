package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.member.entity.Member;
import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {
    private String content;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
    private Member member;

    public MessageResponseDto(Message message) {
        this.content = message.getContent();
        this.createDt=message.getCreateDt();
        this.updateDt=message.getUpdateDt();
        this.member=message.getMember();
    }
}
