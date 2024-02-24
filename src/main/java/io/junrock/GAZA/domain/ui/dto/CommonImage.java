package io.junrock.GAZA.domain.ui.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonImage {
    private CommonUrl imageUrl;

    public static CommonImage toDto(String imageUrl) {
        final CommonUrl url = CommonUrl.builder().url(imageUrl).build();
        return CommonImage
                .builder()
                .imageUrl(url)
                .build();
    }
}
