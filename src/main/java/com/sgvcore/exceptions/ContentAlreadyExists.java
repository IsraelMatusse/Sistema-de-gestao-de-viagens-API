package com.sgvcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ContentAlreadyExists extends Exception {
    public ContentAlreadyExists(String message){
        super(message);
    }
}
