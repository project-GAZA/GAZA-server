package io.junrock.GAZA.auth.service;

import io.junrock.GAZA.domain.admin.entity.Admin;
import io.junrock.GAZA.domain.admin.repostitory.AdminRepository;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    @Transactional
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(final String adminName) {

        return adminRepository.findOneWithAuthoritiesByAdminName(adminName)
                .map(admin -> createAdmin(adminName, admin))
                .orElseThrow(() -> new GazaException(ErrorCode.NOt_FOUND_ADMIN));
    }

    private User createAdmin(String username, Admin admin) {
        if (!admin.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = admin.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());
        System.out.println(grantedAuthorities);

        return new User(admin.getAdminName(),
                admin.getPassword(),
                grantedAuthorities);
    }
}