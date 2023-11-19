package io.junrock.GAZA.domain.memberip.dto;

import io.junrock.GAZA.domain.memberip.entity.MemberIp;
import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpResponseDto {
    private String ip;
    private Message message;

    public IpResponseDto(MemberIp memberIp) {
        this.ip = memberIp.getIp();
        this.message=memberIp.getMessage();
    }
}
