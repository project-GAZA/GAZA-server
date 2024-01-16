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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.junrock.GAZA.mapper.donatemapper.DonateMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
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
        Donate donate = donateDtoMapper(donateDto, messageSubId);
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
        return donateResponseDto(dto, donate);
    }

    private Donate getDonate(Long donateId) {
        return donateRepository.findById(donateId).orElseThrow(()
                -> new GazaException(ErrorCode.NOT_FOUND_DONATE));
    }
}


