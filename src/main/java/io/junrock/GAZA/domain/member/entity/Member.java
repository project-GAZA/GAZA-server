package io.junrock.GAZA.domain.member.entity;

import io.junrock.GAZA.domain.member.dto.MemberInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Authority> authorities;

    public void memberUpdate(MemberInfoDto updateDto){
        this.email=updateDto.getEmail();
        this.userName=updateDto.getUserName();
        this.password=updateDto.getPassword();
    }
}
