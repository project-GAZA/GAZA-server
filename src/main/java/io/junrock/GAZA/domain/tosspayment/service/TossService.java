package io.junrock.GAZA.domain.tosspayment.service;

import io.junrock.GAZA.domain.tosspayment.dto.TossRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TossService {
    private static final String ORDER_ID_PREFIX="gaza-donation-";
    private static final String CUSTOMER_EMAIL="gazadonate@gmail.com";
    private static final String CUSTOMER_NAME="GAZA-Project";
    private static final String ORDER_NAME="gaza-donations";


    public TossRequest getInfo(int price){
        String randomOrderId=UUID.randomUUID().toString();
        String orderId=ORDER_ID_PREFIX+randomOrderId;
        TossRequest tossRequest=new TossRequest();
        tossRequest.setPrice(price);
        tossRequest.setOrderId(orderId);
        tossRequest.setCustomerEmail(CUSTOMER_EMAIL);
        tossRequest.setCustomerName(CUSTOMER_NAME);
        tossRequest.setOrderName(ORDER_NAME);
        return tossRequest;
    }
}
