package com.example.filestorageapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertThrows;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MinioIntegrationTest {

    private AmazonS3 s3Client;

    private static final GenericContainer<?> minioContainer = new GenericContainer<>("minio/minio")
            .withExposedPorts(9900);

    private static final String MINIO_ENDPOINT = "http://localhost:9900";
    private static final String ACCESS_KEY = "filestorage_test";
    private static final String SECRET_KEY = "filestorage1978";
    private static final String BUCKET_NAME = "filestorage-pub";

    @BeforeAll
    public void setUp() {
        initializeS3Client();
    }

    @AfterAll
    void closeMinio() {
        minioContainer.close();
    }

    private void initializeS3Client() {
        String name = Regions.US_EAST_1.getName();
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(MINIO_ENDPOINT, name);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .withEndpointConfiguration(endpoint)
                .withPathStyleAccessEnabled(true)
                .build();
    }

    private void createBucket() {
        s3Client.createBucket(BUCKET_NAME);
    }

    @Test
    @Order(1)
    void bucketExists() {
        Assertions.assertTrue(s3Client.doesBucketExistV2(BUCKET_NAME));
    }

    @Test
    @Order(2)
    void putObjectToBucket() throws Exception {
        String key = "s3-put-object-test.txt";
        String content = "Minio Integration test";

        s3Client.putObject(BUCKET_NAME, key, content);

        S3Object object = s3Client.getObject(BUCKET_NAME, key);

        byte[] actualContent = content.getBytes();
        object.getObjectContent().read(actualContent);
        Assertions.assertEquals(content, new String(actualContent));
    }

    @Test
    @Order(3)
    void removeObjectFromBucket() throws Exception {
        String key = "s3-put-object-test.txt";
        s3Client.deleteObject(BUCKET_NAME, key);

        Exception exception = assertThrows(AmazonServiceException.class, () -> {
            s3Client.getObject(BUCKET_NAME, key);
        });

        String expectedMessage = "The specified key does not exist.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
