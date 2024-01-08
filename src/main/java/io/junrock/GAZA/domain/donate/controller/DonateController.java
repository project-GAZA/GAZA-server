package io.junrock.GAZA.domain.donate.controller;

import io.junrock.GAZA.domain.donate.dto.AmountDto;
import io.junrock.GAZA.domain.donate.dto.DonateDto;
import io.junrock.GAZA.domain.donate.dto.DonateResponseDto;
import io.junrock.GAZA.domain.donate.service.DonateService;
import io.junrock.GAZA.domain.message.dto.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.junrock.GAZA.domain.message.controller.HomeController.pageGenerate;
import static io.junrock.GAZA.domain.message.dto.TypeMessage.DONATE;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donate")
public class DonateController {
    private final DonateService donateService;

    @GetMapping
    public ResponseEntity<Integer> donateSum(){
        return ResponseEntity.ok(donateService.donateSum());
    }

    @PostMapping
    public ResponseEntity<DonateDto> donateTo(@RequestBody DonateDto donateDto){
        return ResponseEntity.ok(donateService.donateMoney(donateDto,DONATE));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DonateResponseDto>> donateList(PageRequestDto pageRequestDto){
        return ResponseEntity.ok(donateService.getList(pageGenerate(pageRequestDto)));
    }

    @PatchMapping("/{donateId}")
    public ResponseEntity<DonateResponseDto> modifyAmount(@PathVariable Long donateId,@RequestBody AmountDto amount){
        return ResponseEntity.ok(donateService.modifyDonate(donateId,amount));
    }
}
