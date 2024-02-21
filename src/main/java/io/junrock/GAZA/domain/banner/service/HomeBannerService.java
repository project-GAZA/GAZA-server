package io.junrock.GAZA.domain.banner.service;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import io.junrock.GAZA.domain.banner.repository.HomeBannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeBannerService {
    private final HomeBannerRepository homeBannerRepository;

    @Cacheable("getHomeLivedHomeBanner")
    public HomeBanner getHomeLivedHomeBanner() {
        return findHomeLivedHomeBannerNoCache();
    }

    public HomeBanner findHomeLivedHomeBannerNoCache() {
        return homeBannerRepository.findTopByActivatedTrueOrderById()
                .orElseThrow(() -> new IllegalArgumentException("There is no lived banner"));
    }
}
