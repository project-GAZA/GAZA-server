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
    private String content;
    private LocalDateTime createDt;
    private String username;
    private Integer likeCount;
    private Integer cautionCount;
    public MessageResponseDto(Message message) {
        this.content =message.getContent();
        this.createDt=message.getCreateDt();
        this.username=message.getUsername();
        this.likeCount=message.getLikeCount();
        this.cautionCount=message.getCautionCount();
    }
}
