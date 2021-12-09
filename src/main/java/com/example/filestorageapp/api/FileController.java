package com.example.filestorageapp.api;

import com.example.filestorageapp.domain.FileDTO;
import com.example.filestorageapp.service.StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cloud")
@RequiredArgsConstructor
public class FileController {

    private final StorageServiceImpl service;

    @PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam(value = "filename") MultipartFile filename) throws IOException {
        service.upload(filename);
        return new ResponseEntity("Success upload.", HttpStatus.OK);
    }

    @DeleteMapping("/file/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        service.delete(fileName);
        return new ResponseEntity<>("Success deleted.", HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] data = service.download(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @PutMapping("/file/{fileName}")
    public ResponseEntity<String> editFilename(@PathVariable(required = true) String fileName,
                                               @RequestParam(required = true) String name) {
        service.renameFile(fileName, name);
        return new ResponseEntity<>("Success renamed.", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}
