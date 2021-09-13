package com.example.bloggingApp.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import com.example.bloggingApp.entities.Article;
import com.example.bloggingApp.exceptions.CustomEntityNotFoundException;
import com.example.bloggingApp.exceptions.NotAuthorizedException;
import com.example.bloggingApp.repository.ArticleRepository;
import com.example.bloggingApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceHelper {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    
    @Autowired
    UserService userService;

    @Autowired
    ArticleRepository articleRepository;

    public String generateSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public void checkUserSameAsLoggedInUser(String email, String message) {
        if(!userService.isUserSameAsLoggedInUser(email)) {
            throw new NotAuthorizedException(message);
        }
    }

    public Article getArticleBySlug(String slug) {
        return articleRepository.findBySlug(slug)
            .orElseThrow(() -> new CustomEntityNotFoundException("No such article exists"));
    }
}
