package com.example.bloggingApp.configurations;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean
    public static SessionFactory getSessionFactory() {
        try {
            return new Configuration().configure(new File("src/main/resources/hibernate.cgf.xml")).buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static void shutdown() {
    }
}
