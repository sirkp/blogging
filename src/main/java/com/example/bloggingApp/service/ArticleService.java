package com.example.bloggingApp.service;

import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.repository.ArticleRepository;
import com.example.bloggingApp.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

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
