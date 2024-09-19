package com.example.apiLivro.exceptions;

public class ServiceException extends RuntimeException{
    public ServiceException(String msg){
        super(msg);
    }
}
