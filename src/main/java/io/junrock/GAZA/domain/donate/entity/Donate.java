package io.junrock.GAZA.domain.donate.entity;

import io.junrock.GAZA.domain.donate.dto.AmountDto;
import io.junrock.GAZA.domain.message.entity.BaseTimeEntity;
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

    @Column(name = "toss_id")
    private String tossId;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "message_id")
    private Long messageSubId ;

    public void update(int amount){
        this.amount=amount;
    }
}
