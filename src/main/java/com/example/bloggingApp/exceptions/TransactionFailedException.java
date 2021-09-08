package com.example.bloggingApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionFailedException extends RuntimeException{
    public TransactionFailedException(String message) {
        super(message);
    }
}
