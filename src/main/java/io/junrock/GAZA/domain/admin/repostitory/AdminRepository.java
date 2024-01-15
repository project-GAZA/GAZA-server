package io.junrock.GAZA.domain.admin.repostitory;

import io.junrock.GAZA.domain.admin.entity.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @EntityGraph(attributePaths = "authorities") // 쿼리를 수행할 때 Eager 조회로 authorities 정보를 가져옴
    Optional<Admin> findOneWithAuthoritiesByAdminName(String adminName);

    boolean existsByAdminName(String adminName);
}
