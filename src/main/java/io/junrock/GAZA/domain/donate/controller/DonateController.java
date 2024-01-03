package io.junrock.GAZA.domain.donate.controller;

import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.service.DonateService;
import io.junrock.GAZA.exception.ApiResponse;
import io.junrock.GAZA.exception.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.junrock.GAZA.domain.message.dto.TypeMessage.DONATE;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donate")
public class DonateController {
    private final DonateService donateService;

    @GetMapping
    public ApiResponse donateSum(){
        return ApiResponse.success(HttpStatusCode.OK,donateService.donateSum());
    }

    @PostMapping
    public ApiResponse<DonateDto> donateTo(@RequestBody DonateDto donateDto){
        return ApiResponse.success(HttpStatusCode.CREATED,donateService.donateMoney(donateDto,DONATE));
    }
}
