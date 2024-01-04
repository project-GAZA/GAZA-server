package io.junrock.GAZA.domain.memberip.service;

import io.junrock.GAZA.domain.memberip.dto.IpResponseDto;
import io.junrock.GAZA.domain.memberip.repository.MemberIpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IpService {
    private final MemberIpRepository memberIpRepository;
    @Transactional
    public boolean checkingIp(String ip,Long messageId,String type){
        List<IpResponseDto> ipList = memberIpRepository.findByIp(ip).stream()
                .map(IpResponseDto::new)
                .collect(Collectors.toList());//해당 ip등을 갖고옴

        return ipList.stream()
                .filter(checkIp -> checkIp.getType().equals(type)) //넘어온 type가 일치한 거만 필터링
                .anyMatch(ipResponseDto -> ipResponseDto.getMessage().getMessageId()==messageId); //일치하면 true, 좋아요, 싫어요 기능제한
    }
}
