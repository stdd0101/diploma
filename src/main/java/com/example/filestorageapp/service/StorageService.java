package com.example.filestorageapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload(MultipartFile filename);
    String delete(String fileName);
    byte[] download(String fileName);
    //File get(String fileName);
    //File update(String fileName);
    //List<File> getFiles();
}
