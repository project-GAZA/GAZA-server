package io.junrock.GAZA.domain.message.service;

import io.junrock.GAZA.domain.message.dto.MessageCountDto;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public Long write(MessageDto messageDto) { //글 작성
        Message message = Message.builder()
                .username(messageDto.getUsername()) //이름은 뒤에 #+PK값으로 중복확인
                .content(messageDto.getContent())
                .userRole("ROLE_USER")
                .likeCount(0)
                .nation(messageDto.getNation())
                .build();
        messageRepository.save(message);
        message.messageUpdate(messageDto.getUsername()+"#"+message.getMessageId());
        return message.getMessageId();
    }

    @Transactional(readOnly = true)
    public List<MessageResponseDto> findAll() {
        return messageRepository.findAll().stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long totalMessage() {
        return messageRepository.count();
    }

    public Integer getCount(Long messageId) {  //메시지 좋아요 기능 추가
        Message message = messageRepository.findById(messageId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 메시지입니다!"));
        messageRepository.updateCount(messageId);
        MessageCountDto messageCountDto=new MessageCountDto(message);
        return messageCountDto.getLikeCount();
    }
}

