package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.aop.Trace;
import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.dto.MessageSearchDto;
import io.junrock.GAZA.domain.message.dto.PageRequestDto;
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
    @Trace
    public ResponseEntity<List<MessageResponseDto>> getList(PageRequestDto pageRequestDto, MessageSearchDto messageSearchDto) {
        return ResponseEntity.ok(messageService.findAllMessages(pageGenerate(pageRequestDto), messageSearchDto));
    }

    public static PageRequest pageGenerate(PageRequestDto dto) {
        int page = dto.getPage();
        int size = dto.getSize();
        return PageRequest.of(page, size);
    }
}
