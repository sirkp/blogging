package com.example.bloggingApp.repository;

import java.util.List;
import java.util.Optional;

import com.example.bloggingApp.entities.Article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findBySlug(String slug);

    @Transactional
    Long deleteBySlug(String slug);
    
    @Query("SELECT DISTINCT article FROM "
            + "Article article JOIN article.user user JOIN article.tags tag "
            + "WHERE (:email IS NULL OR user.email = :email) "
            + "AND (:isTagEmptyOrNull IS TRUE OR tag.name IN :tags) "
            + "AND (:title IS NULL OR article.title LIKE %:title%) "
            + "ORDER BY article.publishedDate DESC LIMIT 0, "
    )
    List<Article> findByUser(
            @Param("email") String email,
            @Param("title") String title, 
            @Param("tags") List<String> tags, 
            @Param("isTagEmptyOrNull") Boolean isTagEmptyOrNull,
            Pageable pageable
        );

}
