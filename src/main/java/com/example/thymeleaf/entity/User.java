package com.example.thymeleaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "roles" , length = 12, nullable = false)
    private String roles;
    @Column(name = "email", unique = true, nullable = false,length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 10)
    private String password;

}
