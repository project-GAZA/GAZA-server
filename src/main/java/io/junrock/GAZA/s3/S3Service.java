package io.junrock.GAZA.s3;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import io.junrock.GAZA.exception.ErrorCode;
import io.junrock.GAZA.exception.GazaException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    private static final String FOLDER_PATH="image/";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        String fileName = FOLDER_PATH+file.getOriginalFilename(); // 사용자가 업로드한 파일명
        objectMetadata.setContentLength(file.getSize()); //파일 크기
        objectMetadata.setContentType(file.getContentType()); //파일 유형
        try {
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), objectMetadata); //파일을 s3에 업로드
            return amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new GazaException(ErrorCode.FAILED_UPLOAD_IMAGE);
        }
    }

    public void deleteS3Image(String fileName){
        try {
            amazonS3Client.deleteObject(bucket,fileName);
        }catch (SdkClientException e){
            throw new GazaException(ErrorCode.EXIST_ADMIN);
        }
    }
}
