package com.sgvcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends Exception{
    public UnprocessableEntity(String message) {
        super(message);
    }
}
