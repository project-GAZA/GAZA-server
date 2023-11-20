package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class MessageLikeController {
    private final MessageService messageService;
    @GetMapping("/{messageId}")
    public ResponseEntity<Integer> likeCounts(@PathVariable Long messageId, HttpServletRequest request){
        return ResponseEntity.ok(messageService.getCount(messageId,request));
    }
}
