package io.junrock.GAZA.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeMessage {
    MESSAGE("message"),
    DONATE("donate");
    private String donateType;
}