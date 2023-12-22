package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSearchDto {
    private String username;
    @Builder.Default
    private String sort="new";
    public Message toEntity(){
        return Message.builder()
                .username(username)
                .donateType(sort)
                .build();
    }
}
