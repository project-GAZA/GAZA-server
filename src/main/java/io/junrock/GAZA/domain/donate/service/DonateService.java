package io.junrock.GAZA.domain.donate.service;

import io.junrock.GAZA.domain.donate.dto.AmountDto;
import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.dto.DonateResponseDto;
import io.junrock.GAZA.domain.donate.entity.Donate;
import io.junrock.GAZA.domain.donate.repository.DonateRepository;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Long messageSubId = messageService.write(messageDto, donateType).getMessageId();
        System.out.println("messageId: "+messageSubId);
        Donate donate = Donate.builder()
                .amount(0)
                .tossId(donateDto.getTossId())
                .telNumber(donateDto.getTelNumber())
                .messageSubId(messageSubId)
                .modifyDt(null)
                .build();
        donateRepository.save(donate);
        return donateDto;
    }

    @Transactional(readOnly = true)
    public Integer donateSum() {
        return donateRepository.sumDonations();
    }

    public List<DonateResponseDto> getList(PageRequest request) {
        return donateRepository.findAll(request).stream()
                .map(DonateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DonateResponseDto modifyDonate(Long donateId, AmountDto dto) {
        Donate donate = getDonate(donateId);
        donate.update(dto.getAmount());
        donateRepository.save(donate);
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

    private Donate getDonate(Long donateId) {
        return donateRepository.findById(donateId).orElseThrow(()
                -> new GazaException(ErrorCode.NOT_FOUND_DONATE));
    }
}


