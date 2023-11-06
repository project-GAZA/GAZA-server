package io.junrock.GAZA.domain.tosspayment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TossConfirmRequest {
   private Integer amount;
   private String orderId;
   private String paymentKey;

   public TossConfirmRequest(Integer amount,String orderId,String paymentKey) {
      this.amount = amount;
      this.orderId = orderId;
      this.paymentKey = paymentKey;
   }
}
