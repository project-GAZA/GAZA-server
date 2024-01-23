package io.junrock.GAZA.domain.message.service;

import io.junrock.GAZA.aop.Trace;
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

import static io.junrock.GAZA.domain.message.dto.ButtonType.CAUTION;
import static io.junrock.GAZA.domain.message.dto.ButtonType.LIKE;
import static io.junrock.GAZA.mapper.messagemapper.MessageMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final IpRenderingService ipRenderingService;
    private final MemberIpRepository memberIpRepository;
    private final IpService ipService;
    public static final int MIN_LENGTH = 1;
    public static final int INITIAL_COUNT=0;
    public static final int PLUS_COUNT=1;

    private final MessageQueryRepository messageQueryRepository;

    @Transactional
    public MessageDonateDto write(MessageDto messageDto, String donateType) { //글 작성
        WordFiltering wordFiltering = new WordFiltering();
        if (messageDto.getUsername().length() <= MIN_LENGTH)
            throw new GazaException(ErrorCode.NOT_ENOUGH_MESSAGE_LENGTH);
        if (wordFiltering.checkMessage(messageDto.getContent()))
            throw new GazaException(ErrorCode.CONTAIN_BADWORD);
        Message message = messageDtoMapper(messageDto, donateType);
        Long messageSubId = messageRepository.save(message).getMessageId();
        message.messageUpdate(messageDto.getUsername() + "#" + message.getMessageId());

        MessageDonateDto messageDonateDto = messageDonateMapper(messageDto, messageSubId);
        return messageDonateDto;
    }

    @Transactional(readOnly = true)
    public Long totalMessage() {
        return messageRepository.count();
    }

    @Transactional
    @Trace
    public Integer getCount(Long messageId, HttpServletRequest request, String buttonType) {  //메시지 좋아요 기능 추가
        Message message = getMessage(messageId);
        String ip = ipRenderingService.getIp(request);
        return extractedCount(messageId, message, ip, buttonType);
    }

    private Message getMessage(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(()
                -> new GazaException(ErrorCode.NOT_FOUND_MESSAGE));
    }

    @Transactional
    public int extractedCount(Long messageId, Message message, String ip, String buttonType) {
        MessageCountDto messageCountDto = new MessageCountDto(message);

        if (ipService.checkingIp(ip, messageId, buttonType)) { //만약 동일한 IP가 좋아요를 누르지 않은 경우
            throw new GazaException(ErrorCode.EXIST_IP);
        }
        int count = INITIAL_COUNT;
        if (buttonType.equals(LIKE.getButtonType())) {
            messageRepository.updateLikeCount(messageId);
            count = messageCountDto.getLikeCount() + PLUS_COUNT;
        }
        if (buttonType.equals(CAUTION.getButtonType())) {
            messageRepository.updateCautionCount(messageId);
            count = messageCountDto.getCautionCount() + PLUS_COUNT;
        }

        MemberIp memberIp = memberIpMapper(message, ip, buttonType);
        memberIpRepository.save(memberIp);
        return count;
    }

    @Transactional(readOnly = true)
    @Trace
    public List<MessageResponseDto> findAllMessages(PageRequest pageGenerate, MessageSearchDto messageSearchDto) {
        return messageQueryRepository.findMessages(pageGenerate, messageSearchDto).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public MessageTypeDto modifyType(Long messageId,MessageTypeDto typeDto) {
        Message message = getMessage(messageId);
        message.modifyType(typeDto.getDonateType());
        messageRepository.save(message);
        return typeDto;
    }

    public MessageResponseDto findMessage(Long messageId) {
        Message message=getMessage(messageId);
        MessageResponseDto responseDto =  messageResponseDto(message);
        return responseDto;
    }
}

