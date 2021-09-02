package com.example.bloggingApp.DTO;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTagDTO {
    
    @NotNull(message = "tags can't be null")
    @Size(min = 1, message = "there should be atleast one tag")
    List<@NotNull(message = "tag can't be null") String> tags;
}
