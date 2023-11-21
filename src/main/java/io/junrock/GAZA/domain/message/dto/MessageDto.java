package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.message.entity.Message;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @NotBlank
    @Size(min=2)
    private String username;
    @NotBlank
    @Size(min = 3,max = 100)
    private String content;

    public Message toEntity() {
        return Message.builder()
                .username(username)
                .content(content)
                .build();
    }
}
