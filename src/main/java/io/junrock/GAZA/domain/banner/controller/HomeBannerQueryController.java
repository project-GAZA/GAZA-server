package io.junrock.GAZA.domain.banner.controller;

import io.junrock.GAZA.domain.banner.service.HomeBannerUiService;
import io.junrock.GAZA.domain.ui.dto.HomeBannerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home-banner")
@RequiredArgsConstructor
@CrossOrigin
public class HomeBannerQueryController{
    private final HomeBannerUiService homeBannerUiService;

    // https://www.figma.com/file/zdUCQxkRGl6L0Ew2cLO3wR/Gaza?type=design&node-id=21-75&mode=design&t=K3xPRZKMwjMTlh66-0
    @GetMapping
    public ResponseEntity<HomeBannerDto> livedHomeBanner() { //불러오는 API
        HomeBannerDto response = homeBannerUiService.getLivedHomeBannerUi();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HomeBannerDto> produceHomeBanner(@RequestBody HomeBannerDto homeBannerDto){
        return ResponseEntity.ok(homeBannerUiService.createHomeBanner(homeBannerDto));
    }
}
