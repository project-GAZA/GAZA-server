package io.junrock.GAZA.domain.banner.service;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import io.junrock.GAZA.domain.ui.dto.*;
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
        final CommonActivated commonActivated = getCommonActivated(homeBanner.getActivated());
        return new HomeBannerDto(commonTitle, commonImage, commonActivated);
    }

    private HomeBanner getLivedHomeBanner() {
        return homeBannerService.getHomeLivedHomeBanner();
    }

    private CommonActivated getCommonActivated(final boolean activated) {
        return CommonActivated.toDto(activated);
    }

    private CommonImage getHomeBannerImage(final String imageUrl) {
        return CommonImage.toDto(imageUrl);
    }

    private CommonTitle getCommonTitle(final HomeBanner homeBanner) {
        return CommonTitle.toDto(homeBanner);
    }

    public HomeBannerDto createHomeBanner(HomeBannerDto homeBannerDto) {
        HomeBanner homeBanner = getHomeBanner(homeBannerDto);
        homeBannerService.saveHomeBanner(homeBanner);
        return homeBannerDto;
    }

    private HomeBanner getHomeBanner(HomeBannerDto homeBannerDto) {
        HomeBanner homeBanner = HomeBanner.builder()
                .mainFirstTitle(homeBannerDto.getCommonTitle().getMainFirstTitle().getText())
                .mainFirstHtml(homeBannerDto.getCommonTitle().getMainFirstTitle().getHtml())
                .mainSecondTitle(homeBannerDto.getCommonTitle().getMainSecondTitle().getText())
                .mainSecondHtml(homeBannerDto.getCommonTitle().getMainSecondTitle().getHtml())
                .mainThirdTitle(homeBannerDto.getCommonTitle().getMainThirdTitle().getText())
                .mainThirdHtml(homeBannerDto.getCommonTitle().getMainThirdTitle().getHtml())
                .imageUrl(homeBannerDto.getCommonImage().getImageUrl().getUrl())
                .activated(homeBannerDto.getCommonActivated().getActivated().isActivated())
                .build();
        return homeBanner;
    }
}
