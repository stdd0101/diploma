package com.example.filestorageapp.api;

import com.example.filestorageapp.service.StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/cloud")
@RequiredArgsConstructor
public class FileController {

    private final StorageServiceImpl service;

    @PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam(value = "filename")  MultipartFile filename) {
        try {
            service.upload(filename);
            return new ResponseEntity("Uploaded", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity("False", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/file/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.delete(fileName), HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.download(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    //@PutMapping("/file/{fileName}")

    //@GetMapping("/list")

}
