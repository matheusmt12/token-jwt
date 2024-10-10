package com.example.thymeleaf.exceptions;

public class NoActiveClienteException extends RuntimeException{
    public NoActiveClienteException(String message){
        super(message);
    }

}
