package com.example.bloggingApp.service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.bloggingApp.DTO.ArticleRequestDTO;
import com.example.bloggingApp.DTO.ArticleResponseDTO;
import com.example.bloggingApp.DTO.ArticleUpdateRequestDTO;
import com.example.bloggingApp.DTO.ListTagDTO;
import com.example.bloggingApp.DTO.TagDTO;
import com.example.bloggingApp.entities.Article;
import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.entities.User;
import com.example.bloggingApp.exceptions.CustomEntityNotFoundException;
import com.example.bloggingApp.exceptions.NotAuthorizedException;
import com.example.bloggingApp.repository.ArticleRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ArticleService {
    
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private static final int PAGE_SIZE = 4;
    
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
        checkUserSameAsLoggedInUser(
                articleRequestDTO.getEmail(),
                "you can't create articles for others");

        Article article = modelMapper.map(articleRequestDTO, Article.class);
        User user = userService.getUserByEmail(articleRequestDTO.getEmail());
        Set<Tag> tags = articleRequestDTO.getTags().stream()
                .map(tagDTO -> tagService.getTag(tagDTO)).collect(Collectors.toSet());

        article.setUser(user);
        article.setTags(tags);
        article.setUuid(UUID.randomUUID().toString());
        article.setSlug(generateSlug(article.getTitle() + "-" + article.getUuid()));
        article = articleRepository.save(article);
        return modelMapper.map(article, ArticleResponseDTO.class);
    }
    
    public ArticleResponseDTO getArticle(String slug) {
        return modelMapper.map(getArticleBySlug(slug), ArticleResponseDTO.class);
    }
    
    public void deleteArticleBySlug(String slug) {
        Article article = getArticleBySlug(slug);
        
        checkUserSameAsLoggedInUser(
                article.getUser().getEmail(),
                "you can only delete your articles");
        
        articleRepository.delete(article);
    }
    
    public ArticleResponseDTO updateArticle(ArticleUpdateRequestDTO articleUpdateRequestDTO, String slug) {
        Article article = getArticleBySlug(slug);
        
        checkUserSameAsLoggedInUser(
                article.getUser().getEmail(),
                "you can only update your articles");
        
        Set<Tag> tags = articleUpdateRequestDTO.getTags().stream()
        .map(tagDTO -> tagService.getTag(tagDTO)).collect(Collectors.toSet());
        
        article.setTitle(articleUpdateRequestDTO.getTitle());
        article.setContent(articleUpdateRequestDTO.getContent());
        article.setTags(tags);
        article.setSlug(generateSlug(article.getTitle() + "-" + article.getUuid()));

        article = articleRepository.save(article);

        return modelMapper.map(article, ArticleResponseDTO.class);
    } 

    public ArticleResponseDTO addTagService(ListTagDTO listTagDTO, String slug) {
        Article article = getArticleBySlug(slug);
        
        checkUserSameAsLoggedInUser(
                article.getUser().getEmail(),
                "you can only update your articles");
        
        for (TagDTO tagDTO: listTagDTO.getTags()) {
            article.addTag(tagService.getTag(tagDTO));
        }        

        article = articleRepository.save(article);

        return modelMapper.map(article, ArticleResponseDTO.class);
    }

    public List<ArticleResponseDTO> getArticles(String email, List<String> tags, String query, Integer page) {
        
        Boolean isTagEmptyOrNull = false;
        if (tags == null || tags.isEmpty()) isTagEmptyOrNull = true;
        
        int pageNumber = (page == null || page.intValue()<0) ? 0 : page.intValue();

        Pageable pageable = Pageable.unpaged();
        if (page != null)
            pageable = PageRequest.of(pageNumber, PAGE_SIZE,
                         Sort.by("publishedDate").descending());

        List<Article> articles = articleRepository.findByUser(email, query, tags, isTagEmptyOrNull,
                pageable);


        return articles.stream().map(
                article -> modelMapper.map(article, ArticleResponseDTO.class)
            ).collect(Collectors.toList());
    }

    private void checkUserSameAsLoggedInUser(String email, String message) {
        if(!userService.isUserSameAsLoggedInUser(email)) {
            throw new NotAuthorizedException(message);
        }
    }

    private Article getArticleBySlug(String slug) {
        return articleRepository.findBySlug(slug)
            .orElseThrow(() -> new CustomEntityNotFoundException("No such article exists"));
    }
    
}
