package io.junrock.GAZA.domain.donate.entity;

import io.junrock.GAZA.domain.message.entity.BaseTimeEntity;
import io.junrock.GAZA.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="donate")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donateId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "message_id")
    private Long messageSubId ;
}
