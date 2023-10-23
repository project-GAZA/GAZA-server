package io.junrock.GAZA.domain.member.repository;

import io.junrock.GAZA.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findOneWithAuthoritiesByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
