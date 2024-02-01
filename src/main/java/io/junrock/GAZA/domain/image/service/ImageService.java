package io.junrock.GAZA.domain.image.service;

import io.junrock.GAZA.domain.image.dto.ImageRequestDto;
import io.junrock.GAZA.domain.image.dto.ImageResponseDto;
import io.junrock.GAZA.domain.image.entity.Image;
import io.junrock.GAZA.domain.image.repository.ImageRepository;
import io.junrock.GAZA.s3.S3Service;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.junrock.GAZA.global.type.ImageType.FOOTER;
import static io.junrock.GAZA.global.type.ImageType.MAIN;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final S3Service s3Uploader;
    private final ImageRepository imageRepository;

    @Transactional
    public Long saveImage(ImageRequestDto requestDto, MultipartFile file) {
        Image image = getImage(requestDto, file);
        return imageRepository.save(image).getImageId();
    }

    @Transactional
    public void delete(Long imageId) {
        Image image = findImage(imageId);
        s3Uploader.deleteS3Image(image.getFileName()); //S3 버킷 이미지 삭제
        imageRepository.deleteById(imageId); // DB이미지 삭제
    }

    @Transactional(readOnly = true)
    public List<ImageResponseDto> getImages() {
        return imageRepository.findAll()
                .stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<ImageResponseDto> getRandomImage() {
        List<ImageResponseDto> mainImage = getMainImage(MAIN.getLocationType());
        List<ImageResponseDto> footerImage = getMainImage(FOOTER.getLocationType());
        List<ImageResponseDto> imageStorage = Arrays.asList(mainImage.get(getRandomIndex(mainImage))
                , footerImage.get(getRandomIndex(footerImage)));
        return imageStorage;
    }

    private int getRandomIndex(List<ImageResponseDto> randomImage) {
        return (int) (Math.random() * randomImage.size());
    }

    @Transactional(readOnly = true)
    private List<ImageResponseDto> getMainImage(String locationType) {
        return imageRepository.findAllByLocationType(locationType)
                .stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private Image getImage(ImageRequestDto requestDto, MultipartFile file) {
        return Image.builder()
                .imageUrl(s3Uploader.uploadImage(file))
                .fileName(file.getOriginalFilename())
                .locationType(requestDto.getLocationType())
                .build();
    }

    @Transactional(readOnly = true)
    private Image findImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new GazaException(ErrorCode.NOT_FOUND_IMAGE));
    }
}
