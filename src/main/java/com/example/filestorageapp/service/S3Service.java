package com.example.filestorageapp.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.List;

@Service
public class S3Service {
    private final AmazonS3 s3client;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public boolean doesBucketExist(String bucketName) {
        return s3client.doesBucketExistV2(bucketName);
    }

    public Bucket createBucket(String bucketName) {
        return s3client.createBucket(bucketName);
    }

    public List<Bucket> listBuckets() {
        return s3client.listBuckets();
    }

    public void deleteBucket(String bucketName) {
        s3client.deleteBucket(bucketName);
    }

    public PutObjectResult putObject(String bucketName, String key, File file) {
        return s3client.putObject(bucketName, key, file);
    }

    public URL getUrl(String bucketName, String key) {
        return s3client.getUrl(bucketName, key);
    }

    public ObjectListing listObjects(String bucketName) {
        return s3client.listObjects(bucketName);
    }

    public CopyObjectResult copyObject(
            String sourceBucketName,
            String sourceKey,
            String destinationBucketName,
            String destinationKey
    ) {
        return s3client.copyObject(
                sourceBucketName,
                sourceKey,
                destinationBucketName,
                destinationKey
        );
    }

    public S3Object getObject(String bucketName, String objectKey) {
        return s3client.getObject(bucketName, objectKey);
    }

    public S3ObjectInputStream getContent(S3Object object) {
        return object.getObjectContent();
    }

    public void deleteObject(String bucketName, String objectKey) {
        s3client.deleteObject(bucketName, objectKey);
    }
}
