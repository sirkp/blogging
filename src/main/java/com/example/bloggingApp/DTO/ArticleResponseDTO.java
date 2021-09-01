package com.example.bloggingApp.DTO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO {
    private String slug;
    
    private String title;
    
    private String content;

    private Date date;

    private List<TagDTO> tags;
    
}
