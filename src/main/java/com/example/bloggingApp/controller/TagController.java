package com.example.bloggingApp.controller;

import com.example.bloggingApp.globals.GlobalConstants;
import com.example.bloggingApp.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = GlobalConstants.APP_NAME)
public class TagController {
    
    @Autowired
    private TagService tagService;

    // public 
}
