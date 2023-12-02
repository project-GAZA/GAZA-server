package io.junrock.GAZA.domain.message.dto;

import lombok.Getter;

@Getter
public class PageRequestDto {
    int page;
    int size;
    PageSort sort;
}
