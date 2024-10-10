package com.example.thymeleaf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "locacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Locacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "data_inicio_locacao",nullable = false)
    private LocalDateTime data_inicio_locacao;
    @Column(name = "data_fim_locacao_previsto")
    private LocalDateTime data_fim_locacao_previsto;
    @Column(name = "data_fim_locacao")
    private LocalDateTime data_fim_locacao;
    @Column(name = "valor", nullable = false)
    private float valor;
    @Column(name = "km_inicial", nullable = false)
    private int km_inicial;
    @Column(name = "km_final")
    private int km_final;
    @Column(name = "finalizada",nullable = false)
    private boolean finalizada;
    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "carro_id",nullable = false)
    private Carro carro;

}
