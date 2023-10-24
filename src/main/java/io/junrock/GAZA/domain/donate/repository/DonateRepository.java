package io.junrock.GAZA.domain.donate.repository;

import io.junrock.GAZA.domain.donate.entity.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonateRepository extends JpaRepository<Donate,Long> {
    @Query("select sum(d.donations) from Donate d")
    Long sumDonations();
}
