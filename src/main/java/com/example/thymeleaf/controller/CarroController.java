package com.example.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.Service.CarroService;
import com.example.thymeleaf.dto.CarroDTO;
import com.example.thymeleaf.entity.Carro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/carro")
public class CarroController {
    
    @Autowired
    private CarroService service; 


    @GetMapping
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }

    @PostMapping
    public ResponseEntity postMethodName(@RequestBody Carro entity) {
        try {
            return new ResponseEntity<>(service.save(entity),HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
 
    }

    @PutMapping("{id}")
    public ResponseEntity putMethodName(@PathVariable long id, @RequestBody CarroDTO carro) {
        try {
            
            return new ResponseEntity<>(service.edit(id, carro),HttpStatus.OK);
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
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }

    }
    
}
