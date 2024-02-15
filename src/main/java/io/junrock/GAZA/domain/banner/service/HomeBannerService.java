package io.junrock.GAZA.domain.banner.service;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import io.junrock.GAZA.domain.banner.repository.HomeBannerRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HomeBannerService {
    private final HomeBannerRepository homeBannerRepository;

    public HomeBannerService(HomeBannerRepository homeBannerRepository) {
        this.homeBannerRepository = homeBannerRepository;
    }

    @Cacheable("getHomeLivedHomeBanner")
    public HomeBanner getHomeLivedHomeBanner() {
        return findHomeLivedHomeBannerNoCache();
    }

    public HomeBanner findHomeLivedHomeBannerNoCache() {
        return homeBannerRepository.findTopByActivatedOrderById()
                .orElseThrow(() -> new IllegalArgumentException("There is no lived banner"));
    }
}
