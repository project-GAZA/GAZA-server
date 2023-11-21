package io.junrock.GAZA.domain.message.entity;

import io.junrock.GAZA.domain.donate.entity.Donate;
import io.junrock.GAZA.domain.memberip.entity.MemberIp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "message")
    private List<MemberIp> memberIp;

    public void messageUpdate(String username){
        this.username=username;
    }
}
