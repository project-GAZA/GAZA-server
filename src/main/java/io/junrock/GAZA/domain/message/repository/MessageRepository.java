package io.junrock.GAZA.domain.message.repository;

import io.junrock.GAZA.aop.Trace;
import io.junrock.GAZA.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Modifying
    @Query("update Message m set m.likeCount=m.likeCount+1 where m.messageId= :messageId")
    @Trace
    void updateLikeCount(@Param("messageId")Long messageId);

    @Modifying
    @Query("update Message m set m.cautionCount=m.cautionCount+1 where m.messageId= :messageId")
    void updateCautionCount(@Param("messageId") Long messageId);

}
