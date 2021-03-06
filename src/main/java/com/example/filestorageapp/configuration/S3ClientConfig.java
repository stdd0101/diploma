package com.example.filestorageapp.configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3ClientConfig {
    @Value("${cloud.aws.s3.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.s3.credentials.secret-key}")
    private String accessSecret;

    @Value("${cloud.aws.s3.region.static}")
    private String region;

    @Value("${application.s3.url}")
    private String url;

    @Bean
    public AmazonS3 s3() {
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(url, region))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(new ClientConfiguration().withSignerOverride("AWSS3V4SignerType"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, accessSecret)))
                .build();
    }
}
