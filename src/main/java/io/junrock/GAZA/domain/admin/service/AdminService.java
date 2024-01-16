package io.junrock.GAZA.domain.admin.service;

import io.junrock.GAZA.domain.admin.dto.SignupDto;
import io.junrock.GAZA.domain.admin.entity.Admin;
import io.junrock.GAZA.domain.admin.entity.Authority;
import io.junrock.GAZA.domain.admin.repostitory.AdminRepository;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public SignupDto signupAdmin(SignupDto signupDto) {
        if (adminRepository.existsByAdminName(signupDto.getAdminName()))
            throw new GazaException(ErrorCode.EXIST_ADMIN);
        Admin admin = Admin.builder()
                .adminName(signupDto.getAdminName())
                .password(encoder.encode(signupDto.getPassword()))
                .activated(true)
                .authorities(Collections.singleton(Authority.ROLE_ADMIN))
                .build();
        adminRepository.save(admin);
        return signupDto;
    }
}
