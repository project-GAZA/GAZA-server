package io.junrock.GAZA.domain.donate.entity;

import io.junrock.GAZA.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;
import org.springframework.jmx.export.annotation.ManagedNotifications;

import javax.persistence.*;

@Entity
@Table(name="donate")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donateId;

    @Column(name = "donations")
    private Long donations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
