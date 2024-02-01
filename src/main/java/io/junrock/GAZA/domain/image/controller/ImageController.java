package io.junrock.GAZA.domain.image.controller;

import io.junrock.GAZA.domain.image.dto.ImageRequestDto;
import io.junrock.GAZA.domain.image.dto.ImageResponseDto;
import io.junrock.GAZA.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadImage(@RequestPart(required = false) ImageRequestDto requestDto,
                            @RequestPart(required = false) MultipartFile file) {
        imageService.saveImage(requestDto, file);
    }

    @DeleteMapping("/{imageId}")
    public void deleteImage(@PathVariable Long imageId){
        imageService.delete(imageId);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> imageList(){
        return ResponseEntity.ok(imageService.getImages());
    }

    @GetMapping("/rand")
    public ResponseEntity<ImageResponseDto> getImage(@RequestParam String locationType){
        return ResponseEntity.ok(imageService.getRandomImage(locationType));
    }
}
