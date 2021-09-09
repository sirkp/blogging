package com.example.bloggingApp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.bloggingApp.DTO.ArticleResponseDTO;
import com.example.bloggingApp.entities.Article;
import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.entities.User;
import com.example.bloggingApp.repository.ArticleRepository;

import org.junit.jupiter.api.Test;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ArticleServiceTest {

    @Spy
    @InjectMocks
    private ArticleService articleService;
    
    @MockBean
    ArticleRepository articleRepository;
    
    @MockBean
    ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getArticleBySlugTest() {
        Article article = getArticle(1, "dummy");

        Mockito.when(articleRepository.findBySlug(eq("spring-boot"))).thenReturn(Optional.of(article));
        Mockito.when(modelMapper.map(any(Article.class), eq(ArticleResponseDTO.class))).thenReturn(getArticleResponseDTOFromArticle(article));
        assertEquals(getArticleResponseDTOFromArticle(article), articleService.getArticle("spring-boot"));
    }
    
    private ArticleResponseDTO getArticleResponseDTOFromArticle(Article article) {
        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO();
        articleResponseDTO.setContent(article.getContent());
        articleResponseDTO.setDate(article.getPublishedDate());
        articleResponseDTO.setTitle(article.getTitle());
        return articleResponseDTO;
    }

    private Article getArticle(Integer id, String title) {
        Article article = new Article();
        article.setId(id);
        article.setUuid(UUID.randomUUID().toString());
        article.setTitle(title);
        article.setContent("content");
        article.setUser(getUser());
        article.setTags(new HashSet<>());
        article.setPublishedDate(new Date());
        return article;
    }

    private HashSet<Tag> getTags(List<String> names) {
        HashSet<Tag> tags = new HashSet<>();
        for (String name : names) {
            tags.add(getTag(name));
        }
        return tags;
    }

    private Tag getTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setArticles(new HashSet<>());
        return tag;
    }

    private User getUser() {
        User user = new User();
        List<Article> articles = new ArrayList<>();
        user.setArticles(articles);
        return user;
    }

}
