package io.junrock.GAZA.domain.image.service;

import io.junrock.GAZA.domain.image.dto.ImageRequestDto;
import io.junrock.GAZA.domain.image.dto.ImageResponseDto;
import io.junrock.GAZA.domain.image.entity.Image;
import io.junrock.GAZA.domain.image.repository.ImageRepository;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public Long saveImage(ImageRequestDto requestDto, MultipartFile file) {
        Image image = getImage(requestDto, file);
        return imageRepository.save(image).getImageId();
    }

    @Transactional
    public void delete(Long imageId) {
        Image image = findImage(imageId);
        imageRepository.deleteById(imageId); // DB이미지 삭제
    }

    @Transactional(readOnly = true)
    public List<ImageResponseDto> getImages() {
        return imageRepository.findAll()
                .stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ImageResponseDto getRandomImage(String locationType) {
        List<ImageResponseDto> mainImage = getMainImage(locationType); //footer이미지만 들어있는 배열
        return mainImage.get(getRandomIndex(mainImage));
    }

    private int getRandomIndex(List<ImageResponseDto> randomImage) {
        return (int) (Math.random() * randomImage.size());
    }

    private List<ImageResponseDto> getMainImage(String locationType) {
        return imageRepository.findAllByLocationType(locationType)
                .stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

    private Image getImage(ImageRequestDto requestDto, MultipartFile file) {
        return Image.builder()
               // .imageUrl(s3Uploader.uploadImage(file))
                .fileName(file.getOriginalFilename())
                .locationType(requestDto.getLocationType())
                .build();
    }

    private Image findImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new GazaException(ErrorCode.NOT_FOUND_IMAGE));
    }
}
