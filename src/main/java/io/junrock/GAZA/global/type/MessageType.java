package io.junrock.GAZA.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    MESSAGE("message"),
    DONATE("donate");
    private String donateType;
}