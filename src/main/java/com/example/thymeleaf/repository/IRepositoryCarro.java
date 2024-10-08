package com.example.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.thymeleaf.entity.Carro;

@Repository 
public interface IRepositoryCarro extends JpaRepository<Carro,Long>{

}
