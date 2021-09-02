package com.example.bloggingApp.repository;

import java.util.Optional;

import com.example.bloggingApp.entities.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByName(String name);
}
