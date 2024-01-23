package io.junrock.GAZA.mapper.adminmapper;

import io.junrock.GAZA.domain.admin.dto.SignupDto;
import io.junrock.GAZA.domain.admin.entity.Admin;
import io.junrock.GAZA.domain.admin.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@RequiredArgsConstructor
public class AdminMapper {
    public static PasswordEncoder encoder;
    public static Admin adminMapper(SignupDto signupDto) {
        Admin admin =  Admin.builder()
                .adminName(signupDto.getAdminName())
                .password(encoder.encode(signupDto.getPassword()))
                .activated(true)
                .authorities(Collections.singleton(Authority.ROLE_ADMIN))
                .build();
        return admin;
    }

}
