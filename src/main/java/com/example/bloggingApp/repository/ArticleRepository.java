package com.example.bloggingApp.repository;

import com.example.bloggingApp.entities.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    
}
