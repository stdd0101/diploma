package com.example.filestorageapp;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.filestorageapp.service.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class S3ServiceTest {

    private static final String BUCKET_NAME = "filestorage-pub";
    private static final String KEY_NAME = "file1.txt";
    private static final String KEY_NAME2 = "file2.txt";

    private AmazonS3 s3;

    private S3Service service;

    @BeforeEach
    public void setUp() {
        s3 = mock(AmazonS3.class);
        service = new S3Service(s3);
    }

    @Test
    public void whenInitializingAWSS3Service_thenNotNull() {
        assertThat(new S3Service(s3)).isNotNull();
    }

    @Test
    public void whenVerifyingIfS3BucketExist_thenCorrect() {
        (new S3Service(s3)).doesBucketExist(BUCKET_NAME);
        verify(s3, atLeastOnce()).doesBucketExistV2(BUCKET_NAME);
    }

    @Test
    public void whenVerifyingCreationOfS3Bucket_thenCorrect() {
        service.createBucket(BUCKET_NAME);
        verify(s3).createBucket(BUCKET_NAME);
    }

    @Test
    public void whenVerifyingPutObject_thenCorrect() {
        File file = mock(File.class);
        PutObjectResult result = mock(PutObjectResult.class);
        when(s3.putObject(anyString(), anyString(), (File) any())).thenReturn(result);

        assertThat(service.putObject(BUCKET_NAME, KEY_NAME, file)).isEqualTo(result);
        verify(s3).putObject(BUCKET_NAME, KEY_NAME, file);
    }

    @Test
    public void whenVerifyingListObjects_thenCorrect() {
        service.listObjects(BUCKET_NAME);
        verify(s3).listObjects(BUCKET_NAME);
    }

    @Test
    public void whenVerifyingGetObject_thenCorrect() {
        service.getObject(BUCKET_NAME, KEY_NAME);
        verify(s3).getObject(BUCKET_NAME, KEY_NAME);
    }

    @Test
    public void whenVerifyingCopyObject_thenCorrect() {
        CopyObjectResult result = mock(CopyObjectResult.class);
        when(s3.copyObject(anyString(), anyString(), anyString(), anyString())).thenReturn(result);

        assertThat(service.copyObject(BUCKET_NAME, KEY_NAME, BUCKET_NAME, KEY_NAME2)).isEqualTo(result);
        verify(s3).copyObject(BUCKET_NAME, KEY_NAME, BUCKET_NAME, KEY_NAME2);
    }

    @Test
    public void whenVerifyingDeleteObject_thenCorrect() {
        service.deleteObject(BUCKET_NAME, KEY_NAME);
        verify(s3).deleteObject(BUCKET_NAME, KEY_NAME);
    }
}
