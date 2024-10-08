package com.example.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.thymeleaf.entity.Cliente;

@Repository
public interface IRepositoryCliente extends JpaRepository<Cliente,Long> {

}
