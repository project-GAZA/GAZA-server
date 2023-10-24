package io.junrock.GAZA.domain.member.service;

import io.junrock.GAZA.domain.member.dto.MemberDto;
import io.junrock.GAZA.domain.member.dto.MemberResponseDto;
import io.junrock.GAZA.domain.member.dto.MemberInfoDto;
import io.junrock.GAZA.domain.member.dto.NonMemberDto;
import io.junrock.GAZA.domain.member.entity.Authority;
import io.junrock.GAZA.domain.member.entity.Member;
import io.junrock.GAZA.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto createMember(MemberDto memberDto) {  //사용자 회원가입
        if (memberRepository.existsByEmail(memberDto.getEmail())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .activated(true)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();

        memberRepository.save(member);
        return memberDto;
    }

    public MemberInfoDto updateMember(MemberInfoDto updateDto, String email) { //사용자 회원정보 수정
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원"));

        member.memberUpdate(updateDto);
        return updateDto;
    }

    public List<MemberResponseDto> findMemberAll() {  //회원 목록 조회
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long deleteMember(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new IllegalStateException("존재하지 않는 회원"));
        memberRepository.delete(member);
        return member.getMemberId();
    }

    public MemberInfoDto findMember(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new IllegalStateException("존재하지 않는 회원"));
        return MemberInfoDto.map(member);
    }

    public NonMemberDto nonMember(NonMemberDto nonMemberDto) {  //비회원 회원가입
        if (memberRepository.existsByEmail(nonMemberDto.getEmail())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        if(nonMemberDto.getUsername()==null)
            nonMemberDto.setUsername(getTempUsername());
        Member member=Member.builder()
                .email(nonMemberDto.getEmail())
                .username(nonMemberDto.getUsername())
                .password(passwordEncoder.encode(nonMemberDto.getPassword()))
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .activated(true)
                .build();

        memberRepository.save(member);
        return nonMemberDto;
    }

    public String getTempUsername() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "Guest";

        int idx = 0;
        for (int i = 0; i < 5; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
