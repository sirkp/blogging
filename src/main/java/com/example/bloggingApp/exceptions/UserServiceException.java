package com.example.bloggingApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}
