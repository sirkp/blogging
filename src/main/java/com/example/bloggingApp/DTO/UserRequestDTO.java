package com.example.bloggingApp.DTO;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
    
    @NotNull(message = "email can't be null")
    @Email(message = "provide valid email") 
    private String email;
    
    @NotNull(message = "name can't be null")
    private String name;
    
    @NotNull(message = "password can't be null")
    @Size(min = 8, message = "password should be atleast 8 chars long")
    private String password;
}   
