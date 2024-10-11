package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.thymeleaf.dto.UserDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/access")
public class AccessController {


    @GetMapping("/login")
    public String postMethodName( ) {
        //TODO: process POST request
        
        return "login";
    }
    
}
