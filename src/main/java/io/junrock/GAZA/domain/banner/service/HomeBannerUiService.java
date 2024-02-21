package io.junrock.GAZA.domain.banner.service;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import io.junrock.GAZA.domain.ui.dto.CommonImage;
import io.junrock.GAZA.domain.ui.dto.CommonTitle;
import io.junrock.GAZA.domain.ui.dto.CommonUrl;
import io.junrock.GAZA.domain.ui.dto.HomeBannerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeBannerUiService {
    private final HomeBannerService homeBannerService;

    public HomeBannerDto getLivedHomeBannerUi() {
        final HomeBanner homeBanner = getLivedHomeBanner();
        final CommonImage commonImage = getHomeBannerImage(homeBanner.getImageUrl());
        final CommonTitle commonTitle = getCommonTitle(homeBanner);
        return new HomeBannerDto(commonTitle, commonImage);
    }

    private HomeBanner getLivedHomeBanner() {
        return homeBannerService.getHomeLivedHomeBanner();
    }

    private CommonImage getHomeBannerImage(final String imageUrl) {
        return CommonImage.toDto(imageUrl);
    }

    private CommonTitle getCommonTitle(final HomeBanner homeBanner) {
        return CommonTitle.toDto(homeBanner);
    }
}
