package io.junrock.GAZA.mapper.donatemapper;

import io.junrock.GAZA.domain.donate.dto.AmountDto;
import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.dto.DonateResponseDto;
import io.junrock.GAZA.domain.donate.entity.Donate;
import org.springframework.stereotype.Component;

@Component
public class DonateMapper {
    public static Donate donateDtoMapper(DonateDto donateDto, Long messageSubId) {
        Donate donate = Donate.builder()
                .amount(0)
                .tossId(donateDto.getTossId())
                .telNumber(donateDto.getTelNumber())
                .messageSubId(messageSubId)
                .modifyDt(null)
                .build();
        return donate;
    }

    public static DonateResponseDto donateResponseDto(AmountDto dto, Donate donate) {
        DonateResponseDto donateResponseDto = DonateResponseDto.builder()
                .donateId(donate.getDonateId())
                .createDt(donate.getCreateDt())
                .amount(dto.getAmount())
                .tossId(donate.getTossId())
                .telNumber(donate.getTelNumber())
                .messageId(donate.getMessageSubId())
                .modifyDt(donate.getModifyDt())
                .build();
        return donateResponseDto;
    }
}
