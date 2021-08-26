package com.example.bloggingApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {

    private String uuid;

    private String name;
    
    private String email;
    
}
