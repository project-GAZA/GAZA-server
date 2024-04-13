package io.junrock.GAZA.domain.ui.dto;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HomeBannerDto {
    private CommonTitle commonTitle;
    private CommonImage commonImage;
    private CommonActivated commonActivated;
}
