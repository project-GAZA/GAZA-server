package io.junrock.GAZA.domain.donate.controller;

import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.service.DonateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.junrock.GAZA.domain.message.dto.TypeMessage.DONATE;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donate")
public class DonateController {
    private final DonateService donateService;

    @PostMapping
    public ResponseEntity<Long> donateTo(@RequestBody DonateDto donateDto){
        return ResponseEntity.ok(donateService.donateMoney(donateDto,DONATE));
    }
}
