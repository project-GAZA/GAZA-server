package io.junrock.GAZA.domain.memberip.entity;

import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member_ip")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberIp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ip_id")
    private Long ipId;

    private String ip;
    // TODO: enum 으로 처리 memberType
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
}
