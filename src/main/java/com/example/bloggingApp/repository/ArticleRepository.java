package com.example.bloggingApp.repository;

import java.util.Optional;

import com.example.bloggingApp.entities.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findBySlug(String slug);

    @Transactional
    Long deleteBySlug(String slug);
}
