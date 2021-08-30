package com.example.bloggingApp.repository;

import com.example.bloggingApp.entities.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}
