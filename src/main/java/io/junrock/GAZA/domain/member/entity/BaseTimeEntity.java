package io.junrock.GAZA.domain.member.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreationTimestamp
    @Column(name = "create_dt")
    private LocalDateTime createDt;

    @UpdateTimestamp
    @Column(name = "update_dt")
    private LocalDateTime updateDt;
}
