package com.example.filestorageapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping({ "/greeting" })
    public String welcomePage() {
        return "Welcome!";
    }

}
