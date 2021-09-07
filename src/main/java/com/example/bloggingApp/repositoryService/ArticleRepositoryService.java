package com.example.bloggingApp.repositoryService;

import java.util.HashSet;
import java.util.Set;

import com.example.bloggingApp.entities.Article;
import com.example.bloggingApp.entities.Tag;
import com.example.bloggingApp.repository.ArticleRepository;
import com.example.bloggingApp.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleRepositoryService{
    
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TagRepository tagRepository;

    public void deleteArticleAndTagOrphan(Article article) {
        Set<Tag> tags = new HashSet<Tag>(article.getTags());

        tags.stream().forEach(tag -> {
            article.removeTag(tag);
        });
       
        articleRepository.delete(article);

        tags.stream().forEach((tag) -> {
            if (tag.getArticles().isEmpty()) tagRepository.delete(tag);
        });

    }

}
