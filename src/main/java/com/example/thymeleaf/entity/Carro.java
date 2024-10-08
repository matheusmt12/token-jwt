package com.example.thymeleaf.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "carro")
public class Carro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "placa", length = 10)
    private String placa;
    @Column(name = "disponivel")
    private boolean disponivel;
    @Column(name = "km")
    private int km;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @OneToMany(mappedBy = "carro")
    private List<Locacoes> locacoes;
    
}
