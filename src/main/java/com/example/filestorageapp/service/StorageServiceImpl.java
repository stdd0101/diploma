package com.example.filestorageapp.service;

import com.amazonaws.AmazonServiceException;
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

    private final S3Service s3service;

    @Value("${application.s3.bucket.name}")
    public String bucketName;

    public StorageServiceImpl(S3Service s3service) {
        this.s3service = s3service;
    }

    //upload files
    public String upload(MultipartFile file) throws IOException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        s3service.putObject(bucketName, fileName, fileObj);
        URL s3Url = s3service.getUrl(bucketName, fileName);

        fileObj.delete();

        return s3Url.toExternalForm();
    }

    //download file
    public byte[] download(String fileName) throws AmazonS3Exception, IOException{
        S3ObjectInputStream inputStream = s3service.getContent(s3service.getObject(bucketName, fileName));
        byte[] content = IOUtils.toByteArray(inputStream);
        return content;
    }

    //delete file
    public void delete(String fileName) throws AmazonServiceException {
        s3service.deleteObject(bucketName, fileName);
    }

    //get all files
    public List<FileDTO> getAll() throws AmazonServiceException {
        List<FileDTO> listUri = new ArrayList<FileDTO>();
        ObjectListing objectListing = s3service.listObjects(bucketName);
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            log.info("object  is " + os.getKey());
            FileDTO file = new FileDTO(os.getKey(), os.getSize());
            listUri.add(file);
        }
        return listUri;
    }

    //rename file
    public void renameFile(String oldFileName, String newFileName) throws AmazonServiceException  {
        log.info("oldFileName: {}, newFileName: {}", oldFileName, newFileName);
        s3service.copyObject(bucketName, oldFileName, bucketName, newFileName);
        s3service.deleteObject(bucketName, oldFileName);
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        return convertedFile;
    }
}
