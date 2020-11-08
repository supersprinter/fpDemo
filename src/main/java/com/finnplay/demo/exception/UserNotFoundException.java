package com.finnplay.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(final String message) {
        super();
        this.message = message;
    }
}

