package io.junrock.GAZA.domain.tosspayment.feigent;

import io.junrock.GAZA.domain.tosspayment.dto.TossConfirmRequest;
import io.junrock.GAZA.domain.tosspayment.dto.TossResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "TossPaymentAPI",
        url = "${tosspayment.api-url}",
        configuration = {TossPaymentConfig.class})
public interface TossPayment {
    @PostMapping
    TossResponse tossResponse(TossConfirmRequest tossConfirmRequest);
}
