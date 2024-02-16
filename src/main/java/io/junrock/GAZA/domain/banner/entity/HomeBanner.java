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

    @Column(name = "main_first_title") //글
    private String mainFirstTitle;
    @Column(name = "main_first_html") //css
    private String mainFirstHtml;

    @Column(name = "main_second_title")
    private String mainSecondTitle;
    @Column(name = "main_second_html")
    private String mainSecondHtml;

    @Column(name = "main_third_title")
    private String mainThirdTitle;
    @Column(name = "main_third_title")
    private String mainThirdHtml;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "activated")
    private Boolean activated;
}
