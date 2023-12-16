package io.junrock.GAZA.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PageRequestDto {
    int page;
    int size;
}
