package io.junrock.GAZA.domain.tosspayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class TossRequest {
    private int price;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
}
