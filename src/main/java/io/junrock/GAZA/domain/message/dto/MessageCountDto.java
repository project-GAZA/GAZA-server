package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCountDto {
    private Integer likeCount;

    public MessageCountDto(Message message){
        this.likeCount=message.getLikeCount();
    }
}
