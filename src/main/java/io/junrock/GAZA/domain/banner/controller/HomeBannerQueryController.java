package io.junrock.GAZA.domain.banner.controller;

import io.junrock.GAZA.domain.banner.service.HomeBannerUiService;
import io.junrock.GAZA.domain.ui.dto.HomeBannerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class HomeBannerQueryController{
    private final HomeBannerUiService homeBannerUiService;

    public HomeBannerQueryController(HomeBannerUiService homeBannerUiService) {
        this.homeBannerUiService = homeBannerUiService;
    }

    // api 명세는 준석이가 화이팅
    // https://www.figma.com/file/zdUCQxkRGl6L0Ew2cLO3wR/Gaza?type=design&node-id=21-75&mode=design&t=K3xPRZKMwjMTlh66-0
    public ResponseEntity<HomeBannerDto> livedHomeBanner() {
        HomeBannerDto response = homeBannerUiService.getLivedHomeBannerUi();
        return ResponseEntity.ok(response);
    }
}
