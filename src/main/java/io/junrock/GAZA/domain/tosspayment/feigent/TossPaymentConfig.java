package io.junrock.GAZA.domain.tosspayment.feigent;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Configuration
public class TossPaymentConfig {
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String SECRET_KEY_KEY = "Authorization";
    private static final String SECRET_KEY_PREFIX = "Basic ";

    private final String secretKey;

    public TossPaymentConfig(@Value("${tosspayment.api-key}") String secretKey) throws UnsupportedEncodingException {
        secretKey=secretKey+":";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
        String authorizations =new String(encodedBytes, 0, encodedBytes.length);
        this.secretKey = SECRET_KEY_PREFIX + authorizations;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
            requestTemplate.header(SECRET_KEY_KEY, secretKey);
        };
    }
}
