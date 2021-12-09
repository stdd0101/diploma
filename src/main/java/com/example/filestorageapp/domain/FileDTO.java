package com.example.filestorageapp.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class FileDTO implements Serializable {
    private String name;
    private long size;

    public FileDTO(String name, long size) {
        this.name = name;
        this.size = size;
    }
}
