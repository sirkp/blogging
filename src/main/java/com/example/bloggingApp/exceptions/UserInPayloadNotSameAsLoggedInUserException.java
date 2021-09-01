package com.example.bloggingApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserInPayloadNotSameAsLoggedInUserException extends RuntimeException {
    public UserInPayloadNotSameAsLoggedInUserException(String message) {
        super(message);
    }
}
