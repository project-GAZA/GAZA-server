package io.junrock.GAZA.domain.member.controller;

import io.junrock.GAZA.auth.utils.SecurityUtil;
import io.junrock.GAZA.domain.member.dto.MemberInfoDto;
import io.junrock.GAZA.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberInfoDto> memberInfo() {
        String email = SecurityUtil.getCurrentUsername();
        return ResponseEntity.ok(memberService.findMember(email));
    }

    @PatchMapping
    public ResponseEntity<MemberInfoDto> updateMember(@RequestBody MemberInfoDto updateDto) {  //회원 정보 수정
        String email = SecurityUtil.getCurrentUsername();
        return ResponseEntity.ok(memberService.updateMember(updateDto, email));
    }

    @DeleteMapping
    public String deleteMember() {  //회원 탈퇴
        String email = SecurityUtil.getCurrentUsername();
        return memberService.deleteMember(email) + "님이 탈퇴하였습니다.";
    }
}
