package io.junrock.GAZA.domain.message.repository;

import io.junrock.GAZA.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
