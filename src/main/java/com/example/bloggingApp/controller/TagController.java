package com.example.bloggingApp.controller;

import javax.print.DocFlavor.STRING;

import com.example.bloggingApp.DTO.ListTagDTO;
import com.example.bloggingApp.globals.GlobalConstants;
import com.example.bloggingApp.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = GlobalConstants.APP_NAME)
public class TagController {
    
    private static final String TAGS_ENDPOINT = "/tags";

    @Autowired
    private TagService tagService;

    @GetMapping(path = TAGS_ENDPOINT)
    public ResponseEntity<ListTagDTO> getAllTags() {
        return new ResponseEntity<>(tagService.getAllTagService(), HttpStatus.OK);
    }
}
