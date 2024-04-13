package io.junrock.GAZA.domain.ui.dto;

import io.junrock.GAZA.domain.banner.entity.HomeBanner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommonTitle {
    private CommonText mainFirstTitle;
    private CommonText mainSecondTitle;
    private CommonText mainThirdTitle;

    public static CommonTitle toDto(final HomeBanner homeBanner) {
        final CommonText mainFirstTitle = new CommonText(homeBanner.getMainFirstTitle(), homeBanner.getMainFirstHtml());
        final CommonText mainSecondTitle = new CommonText(homeBanner.getMainSecondTitle(), homeBanner.getMainSecondHtml());
        final CommonText mainThirdTitle = new CommonText(homeBanner.getMainThirdTitle(), homeBanner.getMainThirdHtml());
        return CommonTitle
                .builder()
                .mainFirstTitle(mainFirstTitle)
                .mainSecondTitle(mainSecondTitle)
                .mainThirdTitle(mainThirdTitle)
                .build();
    }
}
