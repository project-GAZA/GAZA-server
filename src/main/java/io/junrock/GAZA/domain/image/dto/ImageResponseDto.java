package io.junrock.GAZA.domain.image.dto;

import io.junrock.GAZA.domain.image.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponseDto {
    private Long imageId;
    private String imageUrl;
    private String locationType;
    private LocalDateTime createDt;

    public ImageResponseDto(Image image){
        this.imageId=image.getImageId();
        this.imageUrl=image.getImageUrl();
        this.locationType=image.getLocationType();
        this.createDt=image.getCreateDt();
    }
}
