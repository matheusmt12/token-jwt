package com.example.thymeleaf.exceptions;

public class PasswordNoFoundException extends RuntimeException{

    public PasswordNoFoundException (String message){
        super(message);
    }
}
