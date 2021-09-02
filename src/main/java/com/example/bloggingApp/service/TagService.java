package com.example.bloggingApp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Tag getTag(TagDTO tagDTO) {
        Optional<Tag> optonalTag = tagRepository.findByName(tagDTO.getName());
        if (optonalTag.isPresent()) {
            return optonalTag.get();
        } else {
            return modelMapper.map(tagDTO, Tag.class);
        }
    }

    public ListTagDTO getAllTagService() {
        return new ListTagDTO(
            tagRepository.findAll().stream().map(
                    tag -> modelMapper.map(tag, TagDTO.class)
            ).collect(Collectors.toList())
        );
    }
    
}
