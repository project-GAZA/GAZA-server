package io.junrock.GAZA.domain.memberip.repository;

import io.junrock.GAZA.domain.memberip.entity.MemberIp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberIpRepository extends JpaRepository<MemberIp,Long> {
    List<MemberIp> findByIp(String ip);
}
