package io.junrock.GAZA.domain.ui.dto;

import lombok.Builder;

@Builder
public class CommonImage {
    private CommonUrl imageUrl;

    public static CommonImage toDto(String imageUrl) {
        final CommonUrl url = CommonUrl.builder().url(imageUrl).build();
        return CommonImage
                .builder()
                .imageUrl(url)
                .build();
    }
}
