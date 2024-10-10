package com.example.thymeleaf.exceptions;

public class CarroIndisponivelException extends RuntimeException {

    public CarroIndisponivelException(String message){
        super(message);
    }
}
