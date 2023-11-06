package io.junrock.GAZA.domain.tosspayment.toss;

import io.junrock.GAZA.domain.tosspayment.dto.TossRequest;
import io.junrock.GAZA.domain.tosspayment.service.TossService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/toss")
public class PaymentTestController {
    private final TossService tossService;

    @GetMapping
    public ModelAndView setPrice(@RequestParam(name = "price") int price, ModelAndView mav){
        TossRequest request = tossService.getInfo(price);
        mav.addObject("data",request);
        mav.setViewName("index.html");
        return mav;
    }
}
