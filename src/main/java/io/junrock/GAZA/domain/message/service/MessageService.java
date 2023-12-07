package io.junrock.GAZA.domain.message.service;

import com.vane.badwordfiltering.BadWordFiltering;
import io.junrock.GAZA.domain.memberip.entity.MemberIp;
import io.junrock.GAZA.domain.memberip.repository.MemberIpRepository;
import io.junrock.GAZA.domain.memberip.service.IpService;
import io.junrock.GAZA.domain.message.dto.*;
import io.junrock.GAZA.domain.message.entity.Message;
import io.junrock.GAZA.domain.message.repository.MessageQueryRepository;
import io.junrock.GAZA.domain.message.repository.MessageRepository;
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
    private static final Long FAIL_NUM = 0L;
    private static final int MIN_LENGTH = 1;

    private final MessageQueryRepository messageQueryRepository;
    public Long write(MessageDto messageDto,String donateType) { //글 작성
        BadWordFiltering wordFiltering=new BadWordFiltering();
        if (messageDto.getUsername().length() > MIN_LENGTH
                &&!wordFiltering.check(messageDto.getContent())) { //닉네임 길이가 한자리거나 미입력한 경우
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

    @Transactional(readOnly = true)
    public Long totalMessage() {
        return messageRepository.count();
    }

    public Integer getCount(Long messageId, HttpServletRequest request, String type) {  //메시지 좋아요 기능 추가
        Message message = getMessage(messageId);
        String ip = ipRenderingService.getIp(request);
        return extractedCount(messageId, message, ip, type);
    }

    // TODO: OrderBy 를 동적으로 해결해서 if 문 없에볼 것 QueryDsl 쓰기
    // 백업디비? -> rds 쓰잖아 (준석 DB) -> snapShot -> s3 저장소에 저장 하루단위로
    // db 1개 write/ read , reader DB 를 따로 write -> reader DB  lack DB 메모리랑 cpu 사용량 알람 해놔야함 50% -> db 스펙업

    //@Transactional(readOnly = true)
    // 컴포즈 ㄴㄴ
    // @Cacheable() Java Heap jvm
    // 1, 10 DB x -> expire time 만료시간 5분 or 10분 1만명
    // DB 인덱스 걸어나야됨.
    // @Cacheable("findAllByLikecountCache")
    /*public List<MessageResponseDto> findAllByLikecountCache(PageRequest request) { //메시지 좋아요 순으로 정렬
        return findAllByLikecount(request);
    }*///

    @Transactional(readOnly = true)
    public List<MessageResponseDto> findAllMessages(PageRequest request,String type) { //메시지 작성된 최신순으로 정렬
        return messageQueryRepository.findMessages(request,type).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MessageResponseDto> findByUsername(MessageSearchDto dto, PageRequest request) { //username을 통해 검섹
        return messageRepository.findByUsernameStartingWith(dto.getUsername(), request).stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    public Integer alertCountService(Long messageId, HttpServletRequest request,String type) {  //싫어요 기능 제한
        Message message = getMessage(messageId);
        String ip=ipRenderingService.getIp(request); //사용자IP 받아옴
        return extractedCount(messageId,message,ip,type);
    }

    private Message getMessage(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 메시지입니다!"));
    }

    private int extractedCount(Long messageId, Message message, String ip, String type) {
        MessageCountDto messageCountDto = new MessageCountDto(message);

        if (ipService.checkingIp(ip, messageId, type)) { //만약 동일한 IP가 좋아요를 누르지 않은 경우
            // log 사용, System.out.println 왜 안좋은지 그리고 logback 의 역할 까지 공부해서 쓰도록해용
            log.info("이미 좋아요 혹은 신고하기를 누르셨습니다!");
            return 0;
        } else {

            int count = 0;
            if (type.equals(LIKE)) {
                messageRepository.updateLikeCount(messageId);
                count= messageCountDto.getLikeCount()+1;
            }
            if(type.equals(CAUTION)) {
                messageRepository.updateCautionCount(messageId);
                count= messageCountDto.getCautionCount()+1;
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
}

