package com.example.bloggingApp.utils;

import javax.persistence.EntityNotFoundException;

import com.example.bloggingApp.DTO.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ArticleExceptionHandler {
    
    
    @ExceptionHandler(HttpMessageNotReadableException .class)
    public ResponseEntity<?> handleMethodArguementNotValidException(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage("bad request");
        return new ResponseEntity<>(errorMessage,  HttpStatus.BAD_REQUEST);
    }
}
