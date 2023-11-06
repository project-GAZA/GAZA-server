package io.junrock.GAZA.domain.tosspayment.toss;

import io.junrock.GAZA.domain.tosspayment.dto.TossConfirmRequest;
import io.junrock.GAZA.domain.tosspayment.dto.TossResponse;
import io.junrock.GAZA.domain.tosspayment.feigent.TossPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final TossPayment tossPayment;
    @GetMapping(value = "success")
    public String paymentResult(
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "amount") Integer amount,
            @RequestParam(value = "paymentKey") String paymentKey) {
        TossConfirmRequest request = new TossConfirmRequest(amount, orderId, paymentKey);
        TossResponse tossResponse = tossPayment.tossResponse(request);
        //tossResponse.getRecognition(request);
        return "home";
    }

    @GetMapping(value = "fail")
    public String paymentResult(
            Model model,
            @RequestParam(value = "message") String message,
            @RequestParam(value = "code") Integer code
    ) throws Exception {

        model.addAttribute("code", code);
        model.addAttribute("message", message);

        return "fail";
    }

}