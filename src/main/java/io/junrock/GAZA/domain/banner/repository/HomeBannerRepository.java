package io.junrock.GAZA.domain.banner.repository;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface HomeBannerRepository extends JpaRepository<HomeBanner, Long> {
    @Transactional(readOnly = true)
    Optional<HomeBanner> findTopByActivatedTrueOrderById();
}
