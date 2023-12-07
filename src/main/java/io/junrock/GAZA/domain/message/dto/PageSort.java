package io.junrock.GAZA.domain.message.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PageSort {
    BEST("best"),
    NEW("new");

    private String value;

    @JsonCreator
    public static PageSort sorting(String value) {
        return Arrays.stream(values()).
                filter(sort -> sort.getValue().equals(value))
                .findAny().orElse(NEW);
    }
}
