package com.example.thymeleaf.exceptions;

public class UsernameNoFoundException extends RuntimeException{

    public UsernameNoFoundException (String message){
        super(message);
    }

}
