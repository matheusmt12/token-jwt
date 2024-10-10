package com.example.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.Service.LocacaoService;
import com.example.thymeleaf.entity.Locacoes;
import com.example.thymeleaf.repository.IRepositoryLocacao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/locacao")
public class LocadocaoController {

    @Autowired
    private LocacaoService service;

    @PostMapping
    public ResponseEntity post(@RequestBody Locacoes locacao) {
        try {
            return new ResponseEntity<>(service.save(locacao),HttpStatus.valueOf(201));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(service.fidAll(),HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    
    
}
