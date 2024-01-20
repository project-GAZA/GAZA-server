package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.service.MessageService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static io.junrock.GAZA.domain.message.dto.ButtonType.CAUTION;


@RestController
@RequestMapping("/api/message/alert")
@RequiredArgsConstructor
@CrossOrigin
public class MessageAlertController {
    private final MessageService messageService;

    @GetMapping("/{messageId}")
    public ResponseEntity<Integer> alertCount(@PathVariable Long messageId, HttpServletRequest request){
        return ResponseEntity.ok(messageService.getCount(messageId,request,CAUTION.getButtonType()));
    }
}
