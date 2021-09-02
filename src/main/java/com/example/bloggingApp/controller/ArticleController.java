package com.example.bloggingApp.controller;


import javax.validation.Valid;

import com.example.bloggingApp.DTO.ArticleRequestDTO;
import com.example.bloggingApp.DTO.ArticleResponseDTO;
import com.example.bloggingApp.DTO.ArticleUpdateRequestDTO;
import com.example.bloggingApp.DTO.ListTagDTO;
import com.example.bloggingApp.globals.GlobalConstants;
import com.example.bloggingApp.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = GlobalConstants.APP_NAME)
public class ArticleController {

    public static final String ARTICLE_ENDPOINT = "/articles";
    public static final String ARTICLE_SLUG = "/{article_slug}";

    @Autowired 
    ArticleService articleService;
    

    @GetMapping(path = ARTICLE_ENDPOINT + ARTICLE_SLUG)
    public ResponseEntity<ArticleResponseDTO> getArticle(@PathVariable(name = "article_slug") String articleSlug) throws Exception {

        return new ResponseEntity<>(articleService.getArticle(articleSlug), HttpStatus.OK);
    }

    @PostMapping(path = ARTICLE_ENDPOINT)
    public ResponseEntity<ArticleResponseDTO> createArticle(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        ArticleResponseDTO articleResponseDTO = articleService.createArticle(articleRequestDTO);
        return new ResponseEntity<>(articleResponseDTO, HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = ARTICLE_ENDPOINT + ARTICLE_SLUG)
    public ResponseEntity<String> deleteArticle(@PathVariable(name = "article_slug") String articleSlug) {
        articleService.deleteArticleBySlug(articleSlug);           
        return new ResponseEntity<>("article deleted", HttpStatus.OK);
    }

    @PutMapping(path = ARTICLE_ENDPOINT + ARTICLE_SLUG) 
    public ResponseEntity<ArticleResponseDTO> updateArticle(@PathVariable(name = "article_slug") String articleSlug,
            @Valid @RequestBody ArticleUpdateRequestDTO articleUpdateRequestDTO)  {

        return new ResponseEntity<>(articleService.updateArticle(articleUpdateRequestDTO, articleSlug),
                HttpStatus.OK);
    }

    @PatchMapping(path = ARTICLE_ENDPOINT + ARTICLE_SLUG) 
    public ResponseEntity<ArticleResponseDTO> addTags(
                @PathVariable(name = "article_slug") String articleSlug,
                @Valid @RequestBody ListTagDTO listTagDTO) {

        return new ResponseEntity<>(articleService.addTagService(listTagDTO, articleSlug), HttpStatus.OK);
    }
}
