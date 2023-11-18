package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/home")
public class HomeController {
    private final MessageService messageService;
    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessageList(@RequestParam("page")int page,
                                                                   @RequestParam("size")int size){ //홈화면 메시지 목록 출력
        PageRequest request=PageRequest.of(page, size);
        return ResponseEntity.ok(messageService.findAll(request));
    }
}
