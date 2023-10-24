package io.junrock.GAZA.domain.message.service;

import io.junrock.GAZA.domain.member.dto.MemberResponseDto;
import io.junrock.GAZA.domain.member.entity.Member;
import io.junrock.GAZA.domain.member.repository.MemberRepository;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    public Long write(MessageDto messageDto,String email) {
        Member member=memberRepository.findByEmail(email)
                        .orElseThrow(()->new IllegalStateException("존재하지 않는 회원"));
        if(messageDto.getContent().length()>100){
           throw new IllegalStateException("메시지내용이 너무 깁니다!");
        }

        Message message = messageDto.toEntity(member);
        return messageRepository.save(message).getMessageId();
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
}
