package io.junrock.GAZA.domain.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonActivated {
    private CommonStatus activated;

    public static CommonActivated toDto(boolean activated) {
        final CommonStatus commonStatus = CommonStatus.builder().activated(activated).build();
        return CommonActivated.builder()
                .activated(commonStatus)
                .build();
    }
}
