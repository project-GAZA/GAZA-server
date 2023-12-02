package io.junrock.GAZA.domain.message.dto;

import lombok.Getter;

@Getter
public enum PageSort {
    BEST("best"),
    NEW("new");

    private final String value;

    PageSort(String value) {
        this.value = value;
    }
}
