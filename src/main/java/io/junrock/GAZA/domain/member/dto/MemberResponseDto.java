package io.junrock.GAZA.domain.member.dto;

import io.junrock.GAZA.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String email;
    private String username;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;


    public MemberResponseDto(Member member) {
        this.email=member.getEmail();
        this.username=member.getUsername();
        this.createDt=member.getCreateDt();
        this.updateDt=member.getUpdateDt();
    }
}
