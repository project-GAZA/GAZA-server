package io.junrock.GAZA.domain.donate.dto;

import io.junrock.GAZA.domain.donate.entity.Donate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class DonateResponseDto {
    private Long donateId;
    private LocalDateTime createDt;
    private int amount;
    private String tossId;
    private String telNumber;
    private Long messageId;
    private LocalDateTime modifyDt;

    public DonateResponseDto(Donate donate) {
        this.donateId = donate.getDonateId();
        this.createDt = donate.getCreateDt();
        this.amount = donate.getAmount();
        this.tossId = donate.getTossId();
        this.telNumber = donate.getTelNumber();
        this.messageId = donate.getMessageSubId();
        this.modifyDt = donate.getModifyDt();
    }
}
