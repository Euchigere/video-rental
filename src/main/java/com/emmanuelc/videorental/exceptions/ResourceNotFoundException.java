package com.emmanuelc.videorental.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AppCustomException {
    public ResourceNotFoundException(final String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
