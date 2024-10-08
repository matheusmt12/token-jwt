package com.example.thymeleaf.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.Service.ClienteService;
import com.example.thymeleaf.dto.ClienteDTO;
import com.example.thymeleaf.entity.Cliente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    private ClienteService service;


    @PostMapping
    public ResponseEntity post(@RequestBody Cliente cliente){
        try {
            return new ResponseEntity<>(service.save(cliente),HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
             return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }

    @GetMapping
    public ResponseEntity get() {
        try {
            return new ResponseEntity<>(service.findAll(),HttpStatus.valueOf(200));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable long id, @RequestBody ClienteDTO cliente) {
        try {
            return new ResponseEntity<>(service.edit(id, cliente),HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            return new ResponseEntity<>(service.delete(id),HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }    
}
