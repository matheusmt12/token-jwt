package com.example.thymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
    private long id;
    private String name;
    private String cpf; 
    private String telefone;
    private String email;
}
