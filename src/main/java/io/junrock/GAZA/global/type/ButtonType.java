package io.junrock.GAZA.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ButtonType {
    CAUTION("alert"),
    LIKE("like");
    private String buttonType;
}
