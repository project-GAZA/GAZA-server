package io.junrock.GAZA.domain.message.service;

import io.junrock.GAZA.domain.memberip.entity.MemberIp;
import io.junrock.GAZA.domain.memberip.repository.MemberIpRepository;
import io.junrock.GAZA.domain.memberip.service.IpService;
import io.junrock.GAZA.domain.message.dto.MessageCountDto;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.dto.MessageSearchDto;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    private final IpRenderingService ipRenderingService;
    private final MemberIpRepository memberIpRepository;
    private final IpService ipService;
    private static final Long FAIL_NUM=0L;
    private static final int MIN_LENGTH=1;
    public Long write(MessageDto messageDto) { //글 작성
        if (messageDto.getUsername().length() > MIN_LENGTH) { //닉네임 길이가 한자리거나 미입력한 경우
            Message message = Message.builder()
                    .username(messageDto.getUsername())
                    .content(messageDto.getContent())
                    .userRole("ROLE_USER")
                    .likeCount(0)
                    .build();
            messageRepository.save(message);
            message.messageUpdate(messageDto.getUsername() + "#" + message.getMessageId());
            return message.getMessageId();
        } //이름은 뒤에 #+PK값으로 중복확인
        return FAIL_NUM;
    }

    @Transactional(readOnly = true)  //페이징 처리
    public List<MessageResponseDto> findAll(PageRequest request) {
        return messageRepository.findAll(request).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long totalMessage() {
        return messageRepository.count();
    }

    public Integer getCount(Long messageId, HttpServletRequest request) {  //메시지 좋아요 기능 추가
        Message message = messageRepository.findById(messageId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 메시지입니다!"));

        String ip = ipRenderingService.getIp(request);

        MessageCountDto messageCountDto = new MessageCountDto(message);

        if (ipService.checkingIp(ip, messageId)) { //만약 동일한 IP가 좋아요를 누르지 않은 경우
            int likeCount = messageRepository.updateCount(messageId);
            MemberIp memberIp = MemberIp.builder()
                    .ip(ip)
                    .message(message)
                    .build();
            memberIpRepository.save(memberIp);
            return likeCount;
        } else {
            System.out.println("이미 좋아요를 눌렀습니다");
            return messageCountDto.getLikeCount();
        }
    }

    @Transactional(readOnly = true)
    public List<MessageResponseDto> findAllByLikecount(PageRequest request) { //메시지 좋아요 순으로 정렬
        return messageRepository.findAllByOrderByLikeCountDesc(request).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MessageResponseDto> findAllByCreateDt(PageRequest request) { //메시지 작성된 최신순으로 정렬
        return messageRepository.findAllByOrderByCreateDtDesc(request).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<MessageResponseDto> findByUsername(MessageSearchDto dto, PageRequest request) { //username을 통해 검섹
        return messageRepository.findByUsernameStartingWith(dto.getUsername(), request).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }
}

