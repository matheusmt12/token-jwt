package com.example.thymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarroAndModeloDTO {
    
    private String name;
    private int num_portas;
    private int lugares;
    private boolean air_bag;
    private boolean abs;

}
