package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.dto.MessageResponseDto;
import io.junrock.GAZA.domain.message.dto.MessageSearchDto;
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
                                                                   @RequestParam("size")int size){ //홈화면 디폴트 시간 순 메시지 목록 출력
        PageRequest request=PageRequest.of(page, size);
        return ResponseEntity.ok(messageService.findAll(request));
    }

    @GetMapping("/bestmessages")
    public ResponseEntity<List<MessageResponseDto>> getBestMessageList(@RequestParam("page")int page,
                                                                       @RequestParam("size")int size){ //인기순 메시지 목록 출력
        PageRequest request=PageRequest.of(page,size);
        return ResponseEntity.ok(messageService.findAllByLikecount(request));
    }

    @GetMapping("/newmessages")
    public ResponseEntity<List<MessageResponseDto>> getNewMessageList(@RequestParam("page")int page,
                                                                       @RequestParam("size")int size){ //최신순 메시지 목록 출력
        PageRequest request=PageRequest.of(page,size);
        return ResponseEntity.ok(messageService.findAllByCreateDt(request));
    }

    @PostMapping
    public ResponseEntity<List<MessageResponseDto>> getSearchUsername(@RequestBody MessageSearchDto dto,
                                                                      @RequestParam("page")int page,
                                                                      @RequestParam("size")int size){
        PageRequest request=PageRequest.of(page, size);
        return ResponseEntity.ok(messageService.findByUsername(dto,request));
    }
}
