package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.dto.MessageDonateDto;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.dto.MessageTypeDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.junrock.GAZA.global.type.MessageType.MESSAGE;

@CrossOrigin
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDonateDto> writeMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageService.write(messageDto, MESSAGE.getDonateType()));  //저장된 메시지 DTO반환
    }

    @GetMapping
    public ResponseEntity<Long> messageCount() {
        return ResponseEntity.ok(messageService.totalMessage());
    }

    @PatchMapping("/type/{messageId}")
    public ResponseEntity<MessageTypeDto> modifyMessageType(@PathVariable Long messageId, @RequestBody MessageTypeDto typeDto) {
        return ResponseEntity.ok(messageService.modifyType(messageId, typeDto));
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> messageDetails(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.findMessage(messageId));
    }
}
