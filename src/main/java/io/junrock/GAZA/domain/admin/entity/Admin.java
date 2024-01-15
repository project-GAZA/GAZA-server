package io.junrock.GAZA.domain.admin.entity;

import io.junrock.GAZA.domain.message.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "admin")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Authority> authorities;
}
