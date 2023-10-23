package io.junrock.GAZA.auth.controller;

import io.junrock.GAZA.auth.dto.LoginDto;
import io.junrock.GAZA.auth.dto.TokenDto;
import io.junrock.GAZA.auth.service.LoginAuthService;
import io.junrock.GAZA.domain.member.dto.MemberDto;
import io.junrock.GAZA.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginAuthService authService;
    private final MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        TokenDto tokenDto = authService.login(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto){ //회원가입
        return ResponseEntity.ok(memberService.createMember(memberDto));
    }
}
