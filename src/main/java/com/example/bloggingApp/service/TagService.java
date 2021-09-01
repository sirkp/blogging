package com.example.bloggingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag createTag(String name) {
        Tag tag = tagRepository.findByName(name);
        if (tag == null) {
            tag = new Tag();
            tag.setName(name);
            tag = tagRepository.save(tag);
        }
        return tag;
    }
    
}
