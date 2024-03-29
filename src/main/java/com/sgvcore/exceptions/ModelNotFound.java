package com.sgvcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFound extends Exception {
    public ModelNotFound(String message){
        super(message);
    }
}
