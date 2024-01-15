package io.junrock.GAZA.auth.utils;

import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static io.junrock.GAZA.auth.utils.SecurityMessage.NOT_EXIST_AUTHENTICATION;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {}

    // getCurrentUsername 메소드의 역할은 Security Cont
    public static String getCurrentUsername() {

        // authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서
        // Request가 들어올 때 SecurityContext에 Authentication 객체를 저장해서 사용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.info(NOT_EXIST_AUTHENTICATION);
            throw new GazaException(ErrorCode.NOT_EXIST_AUTHENTICATION);
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return username;
    }
}