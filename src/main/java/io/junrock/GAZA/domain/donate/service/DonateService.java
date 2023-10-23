package io.junrock.GAZA.domain.donate.service;

import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.entity.Donate;
import io.junrock.GAZA.domain.donate.repository.DonateRepository;
import io.junrock.GAZA.domain.member.entity.Member;
import io.junrock.GAZA.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DonateService {
    private final DonateRepository donateRepository;
    private final MemberRepository memberRepository;
    public Long donateMoney(DonateDto donateDto, String email) {
        Member member=memberRepository.findByEmail(email)
                .orElseThrow(()->new IllegalStateException("존재하지 않는 회원"));

        Donate donate=Donate.builder()
                .donations(donateDto.getDonations())
                .member(member)
                .build();
       return donateRepository.save(donate).getDonateId();
    }
}
