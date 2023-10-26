package io.junrock.GAZA.domain.member.controller;

import io.junrock.GAZA.domain.member.dto.MemberResponseDto;
import io.junrock.GAZA.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/member")
@RequiredArgsConstructor
public class MemberAdminController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> memberList(){ //관리자만 접근가능한 회원목록 조회
        return ResponseEntity.ok(memberService.findMemberAll());
    }
}
