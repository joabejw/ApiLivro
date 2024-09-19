package com.example.apiLivro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DomainException extends RuntimeException{
    public DomainException(String msg){
        super(msg);
    }
    public DomainException(String msg, Throwable causa){
        super(causa);
    }

}
