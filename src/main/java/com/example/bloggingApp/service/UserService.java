package com.example.bloggingApp.service;

import java.util.Arrays;
import java.util.UUID;

import com.example.bloggingApp.DTO.UserRequestDTO;
import com.example.bloggingApp.DTO.UserResponseDTO;
import com.example.bloggingApp.entities.User;
import com.example.bloggingApp.exceptions.UserServiceException;
import com.example.bloggingApp.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    ModelMapper modelMapper;

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.getEmail()) != null) {
            throw new UserServiceException(userRequestDTO.getEmail() + " already exists");
        }

        User user = modelMapper.map(userRequestDTO, User.class);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole();

        user = userRepository.save(user);
    
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(authority));
    }

    
}
