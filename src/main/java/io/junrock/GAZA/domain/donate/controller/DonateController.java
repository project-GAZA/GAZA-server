package io.junrock.GAZA.domain.donate.controller;

import io.junrock.GAZA.auth.utils.SecurityUtil;
import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.service.DonateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donate")
public class DonateController {
    private final DonateService donateService;

    @PostMapping
    public Long donateTo(@RequestBody DonateDto donateDto){
        String email= SecurityUtil.getCurrentUsername();
        return donateService.donateMoney(donateDto,email); //현재 기부금 총합 반환
    }
}
