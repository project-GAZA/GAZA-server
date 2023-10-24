package io.junrock.GAZA.domain.message.dto;

import io.junrock.GAZA.domain.member.entity.Member;
import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.annotation.security.DenyAll;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    @NotBlank
    @Size(min = 3,max = 100)
    private String content;

    public Message toEntity(Member member) {
        return Message.builder()
                .content(content)
                .member(member)
                .build();
    }
}
