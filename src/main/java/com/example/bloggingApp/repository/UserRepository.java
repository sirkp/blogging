package com.example.bloggingApp.repository;

import java.util.Optional;

import com.example.bloggingApp.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
