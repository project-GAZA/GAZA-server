package io.junrock.GAZA.domain.message.service;

import io.junrock.GAZA.domain.memberip.entity.MemberIp;
import io.junrock.GAZA.domain.memberip.repository.MemberIpRepository;
import io.junrock.GAZA.domain.memberip.service.IpService;
import io.junrock.GAZA.domain.message.dto.*;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.repository.MessageQueryRepository;
import io.junrock.GAZA.domain.message.repository.MessageRepository;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import io.peaceingaza.filtering.cusswordfilter.WordFiltering;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static io.junrock.GAZA.domain.message.dto.TypeMessage.CAUTION;
import static io.junrock.GAZA.domain.message.dto.TypeMessage.LIKE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final IpRenderingService ipRenderingService;
    private final MemberIpRepository memberIpRepository;
    private final IpService ipService;
    private static final int MIN_LENGTH = 1;

    private final MessageQueryRepository messageQueryRepository;

    public MessageDto write(MessageDto messageDto, String donateType) { //글 작성
        WordFiltering wordFiltering = new WordFiltering();
        if (messageDto.getUsername().length() <= MIN_LENGTH)
            throw new GazaException(ErrorCode.NOT_ENOUGH_MESSAGE_LENGTH);
        if (wordFiltering.checkMessage(messageDto.getContent()))
            throw new GazaException(ErrorCode.CONTAIN_BADWORD);
        Message message = Message.builder()
                .username(messageDto.getUsername())
                .content(messageDto.getContent())
                .userRole("ROLE_USER")
                .likeCount(0)
                .cautionCount(0)
                .donateType(donateType)
                .build();
        messageRepository.save(message);
        message.messageUpdate(messageDto.getUsername() + "#" + message.getMessageId());
        return messageDto;
    }

    @Transactional(readOnly = true)  //페이징 처리
    public List<MessageResponseDto> findAll(PageRequest request) {
        return messageRepository.findAll(request).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long totalMessage() {
        return messageRepository.donateMessageCount();
    }

    public Integer getCount(Long messageId, HttpServletRequest request, String type) {  //메시지 좋아요 기능 추가
        Message message = getMessage(messageId);
        String ip = ipRenderingService.getIp(request);
        return extractedCount(messageId, message, ip, type);
    }

    public Integer alertCountService(Long messageId, HttpServletRequest request, String type) {  //싫어요 기능 제한
        Message message = getMessage(messageId);
        String ip = ipRenderingService.getIp(request); //사용자IP 받아옴
        return extractedCount(messageId, message, ip, type);
    }

    private Message getMessage(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 메시지입니다!"));
    }

    private int extractedCount(Long messageId, Message message, String ip, String type) {
        MessageCountDto messageCountDto = new MessageCountDto(message);

        if (ipService.checkingIp(ip, messageId, type)) { //만약 동일한 IP가 좋아요를 누르지 않은 경우
            log.info("이미 좋아요 혹은 신고하기를 누르셨습니다!");
            return 0;
        } else {

            int count = 0;
            if (type.equals(LIKE)) {
                messageRepository.updateLikeCount(messageId);
                count = messageCountDto.getLikeCount() + 1;
            }
            if (type.equals(CAUTION)) {
                messageRepository.updateCautionCount(messageId);
                count = messageCountDto.getCautionCount() + 1;
            }

            MemberIp memberIp = MemberIp.builder()
                    .ip(ip)
                    .message(message)
                    .type(type)
                    .build();
            memberIpRepository.save(memberIp);
            return count;
        }
    }

    @Transactional(readOnly = true)
    public List<MessageResponseDto> findAllMessages(PageRequest pageGenerate, MessageSearchDto messageSearchDto) {
        return messageQueryRepository.findMessages(pageGenerate, messageSearchDto).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());

    }
}

