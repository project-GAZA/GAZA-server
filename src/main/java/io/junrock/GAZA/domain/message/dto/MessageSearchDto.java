package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSearchDto {
    private String username;
    private int page;
    private int size;

    public Message toEntity(){
        return Message.builder()
                .username(username)
                .build();
    }
}
