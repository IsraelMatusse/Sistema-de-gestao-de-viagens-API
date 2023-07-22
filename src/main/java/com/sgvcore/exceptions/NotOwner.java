package com.sgvcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotOwner extends Exception {
    public NotOwner(String message){
        super(message);
    }
}
