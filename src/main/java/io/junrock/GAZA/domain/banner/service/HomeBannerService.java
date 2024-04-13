package io.junrock.GAZA.domain.banner.service;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import io.junrock.GAZA.domain.banner.repository.HomeBannerRepository;
import io.junrock.GAZA.domain.ui.dto.HomeBannerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void saveHomeBanner(HomeBanner homeBanner) {
        homeBannerRepository.save(homeBanner);
    }
}
