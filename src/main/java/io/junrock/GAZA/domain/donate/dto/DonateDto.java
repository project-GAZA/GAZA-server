package io.junrock.GAZA.domain.donate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonateDto {
    private String tossId;
    private String username;
    private String content;
    private String telNumber;
}
