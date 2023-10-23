package io.junrock.GAZA.domain.donate.controller;

import io.junrock.GAZA.auth.utils.SecurityUtil;
import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.service.DonateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donate")
public class DonateController {
    private final DonateService donateService;

    @PostMapping
    public Long donateTo(@RequestBody DonateDto donateDto){
        String email= SecurityUtil.getCurrentUsername();
        return donateService.donateMoney(donateDto,email);
    }
}
