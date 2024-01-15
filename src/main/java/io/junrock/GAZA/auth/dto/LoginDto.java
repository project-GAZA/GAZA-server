package io.junrock.GAZA.auth.dto;

import com.sun.istack.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    private String adminName;

    @NotNull
    private String password;
}