package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.dto.MessageListRequestDto;
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
    private final static String BEST = "best";
    private final static String NEW = "new";

    // TODO RequestBody 로 할것 PageRequest 사용
    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessageList(@RequestParam(value = "page") int page,
                                                                   @RequestParam(value = "size") int size,
                                                                   @RequestParam(value = "sort", defaultValue = NEW) String sort) { //홈화면 디폴트 시간 순 메시지 목록 출력
        PageRequest request = PageRequest.of(page, size);
        // TODO : pageRequest parameter 인자로 넘기기 pageGenerate(null);
        if (sort.equals(BEST)) {
            return ResponseEntity.ok(messageService.findAllByLikecountCache(request));
        }
        if (sort.equals(NEW)) {
            return ResponseEntity.ok(messageService.findAllByCreateDt(request));
        }

        return ResponseEntity.ok(messageService.findAll(request));
    }

    @PostMapping
    public ResponseEntity<List<MessageResponseDto>> getSearchUsername(@RequestBody MessageSearchDto dto,
                                                                      @RequestParam("page") int page,
                                                                      @RequestParam("size") int size) {
        PageRequest request = PageRequest.of(page, size);
        return ResponseEntity.ok(messageService.findByUsername(dto, request));
    }

    private PageRequest pageGenerate(PageRequestDto dto) {
        int page = dto.getPage();
        int size = dto.getSize();
        return PageRequest.of(page, size);
    }
}
