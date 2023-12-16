package io.junrock.GAZA.domain.message.repository;

import io.junrock.GAZA.domain.message.dto.PageRequestDto;
import io.junrock.GAZA.domain.message.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUsernameStartingWith(String username, Pageable pageable);

    @Modifying
    @Query("update Message m set m.likeCount=m.likeCount+1 where m.messageId= :messageId")
    void updateLikeCount(@Param("messageId")Long messageId);

    @Modifying
    @Query("update Message m set m.cautionCount=m.cautionCount+1 where m.messageId= :messageId")
    void updateCautionCount(@Param("messageId") Long messageId);

    @Query("select count (m) from Message m where m.donateType='donate'")
    Long donateMessageCount();
}
