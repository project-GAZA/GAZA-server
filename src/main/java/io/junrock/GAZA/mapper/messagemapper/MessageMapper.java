package io.junrock.GAZA.mapper.messagemapper;

import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.entity.Message;
import org.springframework.stereotype.Component;

import static io.junrock.GAZA.domain.message.service.MessageService.INITIAL_COUNT;

@Component
public class MessageMapper {
    public static Message messageDtoMapper(MessageDto messageDto, String donateType) {
        Message message = Message.builder()
                .username(messageDto.getUsername())
                .content(messageDto.getContent())
                .userRole("ROLE_USER")
                .likeCount(INITIAL_COUNT)
                .cautionCount(INITIAL_COUNT)
                .donateType(donateType)
                .build();
        return message;
    }
}
