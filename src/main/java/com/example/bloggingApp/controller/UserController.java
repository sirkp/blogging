package com.example.bloggingApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    

    @GetMapping(path = "/")
    public String helloWorld() {
        return "Hello World";
    }
}
