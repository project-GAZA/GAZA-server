package io.junrock.GAZA.domain.donate.repository;

import io.junrock.GAZA.domain.donate.entity.Donate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateRepository extends JpaRepository<Donate,Long> {
}
