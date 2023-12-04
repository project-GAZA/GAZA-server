package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.dto.MessageSearchDto;
import io.junrock.GAZA.domain.message.dto.PageRequestDto;
import io.junrock.GAZA.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/home")
public class HomeController {
    private final MessageService messageService;
    private final static String NEW = "new";

    @PostMapping("/test")
    public ResponseEntity<List<MessageResponseDto>> getMessageList(@RequestBody PageRequestDto pageRequestDto,
                                                                   @RequestParam(value = "sort", defaultValue = NEW) String sort) { //홈화면 디폴트 시간 순 메시지 목록 출력
        return ResponseEntity.ok(messageService.findAllMessages(pageGenerate(pageRequestDto),sort));
    }

    @PostMapping
    public ResponseEntity<List<MessageResponseDto>> getSearchUsername(@RequestBody MessageSearchDto messageSearchDto,
                                                                      @RequestBody PageRequestDto pageRequestDto) {
        PageRequest request = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        return ResponseEntity.ok(messageService.findByUsername(messageSearchDto, request));
    }

    private PageRequest pageGenerate(PageRequestDto dto) {
        int page = dto.getPage();
        int size = dto.getSize();
        return PageRequest.of(page, size);
    }
}
