package com.example.bloggingApp.DTO;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    
    @NotNull(message = "tag name can't be null")
    String name;
}
