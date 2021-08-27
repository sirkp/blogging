package com.example.bloggingApp.service;

import java.util.UUID;

import com.example.bloggingApp.DTO.UserRequestDTO;
import com.example.bloggingApp.DTO.UserResponseDTO;
import com.example.bloggingApp.entities.User;
import com.example.bloggingApp.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired ModelMapper modelMapper;

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        User user = modelMapper.map(userRequestDTO, User.class);
        user.setUuid(UUID.randomUUID().toString());

        user = userRepository.save(user);
    
        return modelMapper.map(user, UserResponseDTO.class);
    }

    
}
