package com.example.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.Service.ModeloService;
import com.example.thymeleaf.dto.MarcaDTO;
import com.example.thymeleaf.dto.ModeloDTO;
import com.example.thymeleaf.entity.Modelo;
import com.example.thymeleaf.repository.IRepositoryModelo;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private IRepositoryModelo repositoryModelo;
    @Autowired
    private ModeloService modeloService;

    @PostMapping
    public ResponseEntity post(@RequestBody Modelo modelo){
        try {
            return new ResponseEntity<>(repositoryModelo.save(modelo),HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(modeloService.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }
}
