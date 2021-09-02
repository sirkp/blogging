package com.example.bloggingApp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import com.example.bloggingApp.DTO.ListTagDTO;
import com.example.bloggingApp.DTO.TagDTO;
import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    ModelMapper modelMapper;

    public Tag createTag(TagDTO tagDTO) {
        Tag tag = tagRepository.findByName(tagDTO.getName());
        if (tag == null) {
            tag = new Tag();
            tag.setName(tagDTO.getName());
            tag = tagRepository.save(tag);
        }
        return tag;
    }

    public ListTagDTO getAllTagService() {
        return new ListTagDTO(
            tagRepository.findAll().stream().map(
                    tag -> modelMapper.map(tag, TagDTO.class)
            ).collect(Collectors.toList())
        );
    }
    
}
