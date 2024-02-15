package io.junrock.GAZA.domain.banner.entity;

import io.junrock.GAZA.domain.message.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "home_banner")
@Getter
@Setter
public class HomeBanner extends BaseTimeEntity {

    //TODO: Collum 바차 사이즈도 준석이ㅏ가 알아서 ㅎㅇㅌ 테이블도 생성해줘 ㅎㅇㅌ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mainFirstTitle;
    @Column
    private String mainFirstHtml;

    @Column
    private String mainSecondTitle;
    @Column
    private String mainSecondHtml;

    @Column
    private String mainThirdTitle;
    @Column
    private String mainThirdHtml;

    @Column
    private String imageUrl;

    @Column
    private Boolean activated;
}
