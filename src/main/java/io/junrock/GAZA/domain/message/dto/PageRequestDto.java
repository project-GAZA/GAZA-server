package io.junrock.GAZA.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class PageRequestDto {
    int page;
    int size;
    //PageSort sort;
}
