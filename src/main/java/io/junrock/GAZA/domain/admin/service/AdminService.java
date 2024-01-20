package io.junrock.GAZA.domain.admin.service;

import io.junrock.GAZA.domain.admin.dto.SignupDto;
import io.junrock.GAZA.domain.admin.entity.Admin;
import io.junrock.GAZA.domain.admin.repostitory.AdminRepository;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.junrock.GAZA.mapper.adminmapper.AdminMapper.adminMapper;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    @Transactional
    public SignupDto signupAdmin(SignupDto signupDto) {
        if (adminRepository.existsByAdminName(signupDto.getAdminName()))
            throw new GazaException(ErrorCode.EXIST_ADMIN);
        Admin admin = adminMapper(signupDto);
        adminRepository.save(admin);
        return signupDto;
    }
}
