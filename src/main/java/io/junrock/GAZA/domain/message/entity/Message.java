package io.junrock.GAZA.domain.message.entity;

import io.junrock.GAZA.domain.donate.entity.Donate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "username")
    private String username;

    @Column(name = "content")
    private String content;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "like_count")
    private Integer likeCount;

    public void messageUpdate(String username){
        this.username=username;
    }
}
