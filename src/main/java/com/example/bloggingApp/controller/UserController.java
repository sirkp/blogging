package com.example.bloggingApp.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.example.bloggingApp.DTO.UserRequestDTO;
import com.example.bloggingApp.DTO.UserResponseDTO;
import com.example.bloggingApp.globals.GlobalConstants;
import com.example.bloggingApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = GlobalConstants.APP_NAME)
public class UserController {
    
    public static final String USERS_ENDPOINT = "/users";

    @Autowired
    private UserService userService;

    @GetMapping(path = "/home")
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping(path = USERS_ENDPOINT)
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.saveUser(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/secured")
    public String securedEndpoint() {
        return "Hello from secured endpoint";
    }

    
}
