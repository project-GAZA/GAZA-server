package io.junrock.GAZA.domain.member.dto;

import io.junrock.GAZA.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {
    private String email;
    private String username;
    private String password;

    public static MemberInfoDto map(Member member) {
        return MemberInfoDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .password(member.getPassword())
                .build();
    }
}
