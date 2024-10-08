package com.example.thymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarroDTO {
    private long id;
    private String placa;
    private boolean disponivel;
    private int km;
    private CarroAndModeloDTO modeloCarro;
}
