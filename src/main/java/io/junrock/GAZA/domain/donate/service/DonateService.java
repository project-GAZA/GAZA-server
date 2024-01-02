package io.junrock.GAZA.domain.donate.service;

import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.entity.Donate;
import io.junrock.GAZA.domain.donate.repository.DonateRepository;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DonateService {
    private final DonateRepository donateRepository;
    private final MessageService messageService;

    @Transactional
    public DonateDto donateMoney(DonateDto donateDto, String donateType) {
        MessageDto messageDto = MessageDto.builder()
                .username(donateDto.getUsername())
                .content(donateDto.getContent())
                .build();
        Long messageSubId = messageService.write(messageDto, donateType).toEntity().getMessageId();
        Donate donate = Donate.builder()
                .amount(0)
                .tossId(donateDto.getTossId())
                .telNumber(donateDto.getTelNumber())
                .messageSubId(messageSubId)
                .build();
        donateRepository.save(donate);
        return donateDto;
    }

    public Integer donateSum() {
        return donateRepository.sumDonations();
    }
}


