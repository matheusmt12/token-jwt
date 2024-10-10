package com.example.thymeleaf.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LocacaoDTO {

    private long codigo;
    private String nameCliente;
    private float valor;
    private String nameCarro;
    private String nameMarca;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataPrevista;
    private LocalDateTime dataFim;
    private int kmInicial;
    private int kmFinal;
    private boolean finalizada;



}
