package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.service.MessageService;
import io.junrock.GAZA.exception.ApiResponse;
import io.junrock.GAZA.exception.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static io.junrock.GAZA.domain.message.dto.TypeMessage.LIKE;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message/likes")
public class MessageLikeController {
    private final MessageService messageService;
    @GetMapping("/{messageId}")
    public ApiResponse likeCounts(@PathVariable Long messageId, HttpServletRequest request){
        return ApiResponse.success(HttpStatusCode.OK,messageService.getCount(messageId,request,LIKE));
    }
}
