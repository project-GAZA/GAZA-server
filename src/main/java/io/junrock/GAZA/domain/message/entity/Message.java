package io.junrock.GAZA.domain.message.entity;

import io.junrock.GAZA.domain.member.entity.BaseTimeEntity;
import io.junrock.GAZA.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Message extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
