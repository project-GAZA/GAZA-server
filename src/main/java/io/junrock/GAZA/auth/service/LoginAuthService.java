package io.junrock.GAZA.auth.service;

import io.junrock.GAZA.auth.dto.LoginDto;
import io.junrock.GAZA.auth.dto.TokenDto;
import io.junrock.GAZA.auth.jwt.TokenProvider;
import io.junrock.GAZA.domain.admin.repostitory.AdminRepository;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class LoginAuthService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AdminRepository adminRepository;

    public TokenDto login(LoginDto loginDto) {
        if (!adminRepository.existsByAdminName(loginDto.getAdminName())) {
            throw new GazaException(ErrorCode.NOt_FOUND_ADMIN);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getAdminName(), loginDto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

        return new TokenDto(jwt);
    }
}
