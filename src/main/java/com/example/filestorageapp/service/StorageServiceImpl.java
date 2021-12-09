package com.example.filestorageapp.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.example.filestorageapp.domain.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StorageServiceImpl {

    private final AmazonS3 amazonS3;

    @Value("${application.s3.bucket.name}")
    public String bucketName;

    public StorageServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String upload(MultipartFile file) throws IOException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, fileObj);
        amazonS3.putObject(request);
        URL s3Url = amazonS3.getUrl(bucketName, fileName);
        log.info("S3 url is " + s3Url.toExternalForm());

        fileObj.delete();

        return s3Url.toExternalForm();
    }

    public byte[] download(String fileName) throws AmazonS3Exception, IOException{
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        byte[] content = IOUtils.toByteArray(inputStream);
        return content;
    }

    public void delete(String fileName) throws AmazonServiceException {
        amazonS3.deleteObject(bucketName, fileName);
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        return convertedFile;
    }

    public List<FileDTO> getAll() throws AmazonServiceException {
        List<FileDTO> listUri = new ArrayList<FileDTO>();
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            log.info("object  is " + os.getKey());
            FileDTO file = new FileDTO(os.getKey(), os.getSize());
            listUri.add(file);
        }
        return listUri;
    }


    public void renameFile(String oldFileName, String newFileName) throws AmazonServiceException  {
        log.info("oldFileName: {}, newFileName: {}", oldFileName, newFileName);
        CopyObjectRequest copyObjRequest = new CopyObjectRequest(bucketName, oldFileName, bucketName, newFileName);
        amazonS3.copyObject(copyObjRequest);
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, oldFileName));
    }

}
