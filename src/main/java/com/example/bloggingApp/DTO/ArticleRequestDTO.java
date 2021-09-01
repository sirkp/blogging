package com.example.bloggingApp.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleRequestDTO {
    
    @NotNull(message = "email can't be null")
    @Email(message = "provide valid email") 
    private String email;
    
    @NotNull(message = "title can't be null")
    private String title;
    
    @NotNull(message = "content can't be null")
    private String content;

    @NotNull(message = "tags can't be null")
    @Size(min = 1, message = "there should be atleast one tag")
    List<@NotNull(message = "tag can't be null") String> tags;
}
