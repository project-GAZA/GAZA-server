package io.junrock.GAZA.domain.message.repository;

import io.junrock.GAZA.domain.message.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Modifying
    @Query("update Message m set m.likeCount=m.likeCount+1 where m.messageId= :messageId")
    int updateCount(Long messageId);

    List<Message> findAllByOrderByLikeCountDesc(Pageable pageable);
    List<Message> findAllByOrderByCreateDtDesc(Pageable pageable);

    List<Message> findByUsernameStartingWith(String username,Pageable pageable);
}
