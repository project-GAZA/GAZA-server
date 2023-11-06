package io.junrock.GAZA.domain.tosspayment.dto;

import io.junrock.GAZA.domain.donate.service.DonateService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TossResponse {
   private final DonateService donateService;
   /*public Long getRecognition(TossConfirmRequest tossConfirmRequest){
      return donateService.donateMoney(tossConfirmRequest.getAmount());
   }*/
}
