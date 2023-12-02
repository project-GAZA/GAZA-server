package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.junrock.GAZA.domain.message.dto.TypeMessage.MESSAGE;

@CrossOrigin
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Long> writeMessage(@RequestBody MessageDto messageDto){
        return ResponseEntity.ok(messageService.write(messageDto,MESSAGE));  //저장된 메시지 ID반환
    }

    @GetMapping
    public ResponseEntity<Long> messageCount(){
        return ResponseEntity.ok(messageService.totalMessage());
    }
}
