package io.junrock.GAZA.domain.message.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class IpRenderingService {
    private String[] headerType = {"X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR",
            "X-Real-IP","REMOTE_ADDR"};
    private String ip;

    public String getIp(HttpServletRequest request){
        for(String header: headerType){
            ip=request.getHeader(header);
            if (ip!=null) break;
        }
        if(ip==null) ip=request.getRemoteAddr();

        return ip;
    }
}
