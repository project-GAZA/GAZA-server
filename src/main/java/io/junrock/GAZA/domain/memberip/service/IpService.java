package io.junrock.GAZA.domain.memberip.service;

import io.junrock.GAZA.domain.memberip.dto.IpResponseDto;
import io.junrock.GAZA.domain.memberip.repository.MemberIpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IpService {
    private final MemberIpRepository memberIpRepository;
    public int checkingIp(String ip,Long messageId,int count){
        List<IpResponseDto> ipList = memberIpRepository.findByIp(ip).stream()
                .map(IpResponseDto::new)
                .collect(Collectors.toList());//해당 ip등을 갖고옴

        for (int i = 0; i < ipList.size(); i++) {
            if (ipList.get(i).getMessage().getMessageId() == messageId) {
                count++;
            }
        }
        return count;
    }
}
