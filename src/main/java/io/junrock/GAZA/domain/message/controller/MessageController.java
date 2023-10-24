package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.auth.utils.SecurityUtil;
import io.junrock.GAZA.domain.message.dto.MessageDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Long> writeMessage(@RequestBody MessageDto messageDto){
        String email= SecurityUtil.getCurrentUsername();
        return ResponseEntity.ok(messageService.write(messageDto,email));
    }

    @GetMapping
    public ResponseEntity<Long> messageCount(){
        return ResponseEntity.ok(messageService.totalMessage());
    }
}
