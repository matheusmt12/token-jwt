package com.example.thymeleaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;
    
    @Column(name = "cpf",length = 15,unique = true,nullable = false)
    private String cpf; 
    @Column(name = "telefone", length = 10)
    private int telefone;
    @Column(name = "email", length = 30,nullable = false)
    private String email;
}
