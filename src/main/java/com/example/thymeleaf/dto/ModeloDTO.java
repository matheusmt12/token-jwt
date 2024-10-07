package com.example.thymeleaf.dto;

import java.util.List;

import com.example.thymeleaf.entity.Carro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModeloDTO {

    private long id;
    private String name;
    private int num_portas;
    private int lugares;
    private boolean air_bag;
    private boolean abs;
    private List<Carro> carros;
}
