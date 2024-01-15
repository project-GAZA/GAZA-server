package io.junrock.GAZA.auth.dto;

import io.junrock.GAZA.domain.admin.entity.Admin;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String adminName;

    private String password;

    public Admin toEntity(){
        return Admin.builder()
                .adminName(adminName)
                .password(password)
                .build();
    }
}
