package io.junrock.GAZA.domain.message.repository;

import io.junrock.GAZA.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Modifying
    @Query("update Message m set m.likeCount=m.likeCount+1 where m.messageId= :messageId")
    void updateCount(Long messageId);
}
