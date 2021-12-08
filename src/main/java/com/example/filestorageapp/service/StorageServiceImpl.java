package com.example.filestorageapp.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        log.info("filename:", fileObj.getName());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded: ";
    }

    public byte[] download(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String delete(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return fileName + " removed.";
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        return convertedFile;
    }

//    protected File convertMultiPartFileToFile(MultipartFile file) throws IOException {
//
//        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//
//        File convFile = new File(fileName);
//        convFile.createNewFile();
//        try (InputStream is = file.getInputStream()) {
//            Files.copy(is, convFile.toPath());
//        }
//        return convFile;
////        File convFile = new File(file.getOriginalFilename());
////        file.transferTo(convFile);
////        return convFile;
//    }
}
