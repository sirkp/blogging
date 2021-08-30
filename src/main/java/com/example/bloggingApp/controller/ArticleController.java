package com.example.bloggingApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.example.bloggingApp.DTO.ArticleRequestDTO;
import com.example.bloggingApp.DTO.ArticleResponseDTO;
import com.example.bloggingApp.entities.Article;
import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.entities.User;
import com.example.bloggingApp.globals.GlobalConstants;
import com.example.bloggingApp.repository.ArticleRepository;
import com.example.bloggingApp.repository.UserRepository;
import com.example.bloggingApp.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = GlobalConstants.APP_NAME)
public class ArticleController {
    public static final String ARTICLE_ENDPOINT = "/articles";
    public static final String ARTICLE_SLUG = "/{articleSlug}";

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired ArticleService articleService;

    @PostMapping(path = ARTICLE_ENDPOINT)
    public ResponseEntity<ArticleResponseDTO> createArticle(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        article.setContent(articleRequestDTO.getContent());
        article.setTitle(articleRequestDTO.getTitle());
        User user = userRepository.findByEmail(articleRequestDTO.getEmail());
        article.setUser(user);
        article.setSlug(articleRequestDTO.getTitle());
        
        List<String> tagNames = articleRequestDTO.getTags();
        for (String name: tagNames) {
            article.addTag(articleService.createTag(name));
        }

        article = articleRepository.save(article);

        return new ResponseEntity<>(new ArticleResponseDTO(article.getSlug(), article.getTitle(),
                 article.getContent(), article.getPublishedDate(), tagNames), HttpStatus.CREATED);
    }

    
    @GetMapping(path = ARTICLE_ENDPOINT + ARTICLE_SLUG)
    public ResponseEntity<ArticleResponseDTO> getArticle(@PathVariable Integer articleSlug) throws Exception {
        Article article = articleRepository.findById(articleSlug)
                .orElseThrow(() -> new Exception("drfedrf"));
        
        List<String> tagNames = new ArrayList<>();
        for (Tag tag: article.getTags()) {
            tagNames.add(tag.getName());
        }

        return new ResponseEntity<>(new ArticleResponseDTO(article.getSlug(), article.getTitle(),
                article.getContent(), article.getPublishedDate(), tagNames), HttpStatus.OK);
    }
    
    @DeleteMapping(path = ARTICLE_ENDPOINT + "/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
                
        Set<Tag> tags = new HashSet<Tag>(article.getTags());
        for(Tag tag: tags) {
            article.removeTag(tag);
        }
        articleRepository.delete(article);

        return new ResponseEntity<>("article deleted", HttpStatus.OK);
    }
}
