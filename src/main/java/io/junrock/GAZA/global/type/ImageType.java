package io.junrock.GAZA.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageType {
    MAIN("main"),
    FOOTER("footer");
    private String locationType;
}
