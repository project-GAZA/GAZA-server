package io.junrock.GAZA.domain.message.dto;

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
    private Long messageId;
    private String content;
    private LocalDateTime createDt;
    private String username;
    private Integer likeCount;
    private Integer cautionCount;
    private String donateType;
    public MessageResponseDto(Message message) {
        this.messageId=message.getMessageId();
        this.content =message.getContent();
        this.createDt=message.getCreateDt();
        this.username=message.getUsername();
        this.likeCount=message.getLikeCount();
        this.cautionCount=message.getCautionCount();
        this.donateType=message.getDonateType();
    }
}
