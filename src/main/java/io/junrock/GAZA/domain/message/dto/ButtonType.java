package io.junrock.GAZA.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ButtonType {
    CAUTION("alert"),
    LIKE("like");
    private String buttonType;
}
