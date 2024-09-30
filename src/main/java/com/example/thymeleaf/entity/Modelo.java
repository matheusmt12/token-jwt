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
@Table(name = "modelo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Modelo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",length = 20)
    private String name;
    @Column(name = "num_portas")
    private int num_portas;
    @Column(name = "lugares")
    private int lugares;
    @Column(name = "air_bag")
    private boolean air_bag;
    @Column(name = "abs")
    private boolean abs;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToMany(mappedBy = "modelo")
    private List<Carro> carros;
}
