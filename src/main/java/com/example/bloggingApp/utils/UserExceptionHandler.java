package com.example.bloggingApp.utils;

import java.util.List;

import com.example.bloggingApp.DTO.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArguementNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        StringBuilder sb =  new StringBuilder();
        List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
        for (FieldError fieldError: fieldErrors) {
            sb.append(fieldError.getDefaultMessage() + ";");
        }
        ErrorMessage errorMessage = new ErrorMessage(sb.toString());
        return new ResponseEntity<>(errorMessage,  HttpStatus.BAD_REQUEST);
    }
}
