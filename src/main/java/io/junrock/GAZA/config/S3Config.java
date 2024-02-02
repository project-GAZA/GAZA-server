package io.junrock.GAZA.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static io.junrock.GAZA.VV.*;

@Configuration
public class S3Config {
    //@Value("${cloud.aws.credentials.accessKey}")
    private String accessKey=ACCESSKEY;

   // @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey=SECRETKEY;

    //@Value("${cloud.aws.region.static}")
    private String region=REGION;

    //@Value("${cloud.aws.s3.endpoint}")
    private String endPoint=ENDPOINT;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCrews = new BasicAWSCredentials(accessKey,secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCrews))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, region))
                .build();
    }
}