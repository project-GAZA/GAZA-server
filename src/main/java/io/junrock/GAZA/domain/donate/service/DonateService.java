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
    public Long donateMoney(DonateDto donateDto, String donateType) {
        MessageDto messageDto = MessageDto.builder()
                .username(donateDto.getUsername())
                .content(donateDto.getContent())
                .build();
        Long messageSubId = messageService.write(messageDto, donateType);
        Donate donate = Donate.builder()
                .amount(donateDto.getAmount())
                .paymentType(donateDto.getPaymentType())
                .paymentKey(donateDto.getPaymentKey())
                .orderId(donateDto.getOrderId())
                .messageSubId(messageSubId)
                .build();
        return donateRepository.save(donate).getMessageSubId();
    }
}


