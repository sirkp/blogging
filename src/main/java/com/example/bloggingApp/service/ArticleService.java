package com.example.bloggingApp.service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.example.bloggingApp.DTO.ArticleRequestDTO;
import com.example.bloggingApp.DTO.ArticleResponseDTO;
import com.example.bloggingApp.entities.Article;
import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.entities.User;
import com.example.bloggingApp.exceptions.NotAuthorizedException;
import com.example.bloggingApp.repository.ArticleRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleService {
    
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired 
    private ModelMapper modelMapper;


    private String generateSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
    
    public ArticleResponseDTO createArticle(ArticleRequestDTO articleRequestDTO) {
        if (!userService.isUserInPayloadSameAsLoggedInUser(articleRequestDTO.getEmail())) {
            throw new NotAuthorizedException("you can't create articles for others");
        }

        Article article = modelMapper.map(articleRequestDTO, Article.class);
        User user = userService.getUserByEmail(articleRequestDTO.getEmail());
        Set<Tag> tags = articleRequestDTO.getTags().stream()
                .map(name -> tagService.createTag(name)).collect(Collectors.toSet());

        article.setUser(user);
        article.setTags(tags);
        article.setSlug(article.getTitle());

        article = articleRepository.save(article);
        article.setSlug(generateSlug(article.getTitle() + "-" + article.getId()));
        article = articleRepository.save(article);

        return modelMapper.map(article, ArticleResponseDTO.class);
    }

    public ArticleResponseDTO getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow();
        return modelMapper.map(article, ArticleResponseDTO.class);
    }

    public void deleteArticleBySlug(String slug) {
        Long noOfArticlesDeleted = articleRepository.deleteBySlug(slug);
        if (noOfArticlesDeleted == 0) {
            throw new EntityNotFoundException("No such article exist");
        }
    }


}
