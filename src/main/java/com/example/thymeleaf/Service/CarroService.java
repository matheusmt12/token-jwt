package com.example.thymeleaf.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dto.CarroAndModeloDTO;
import com.example.thymeleaf.dto.CarroDTO;
import com.example.thymeleaf.entity.Carro;
import com.example.thymeleaf.entity.Modelo;
import com.example.thymeleaf.exceptions.NoFindCarroException;
import com.example.thymeleaf.repository.IRepositoryCarro;

import jakarta.transaction.Transactional;

@Service
public class CarroService {

    @Autowired
    private final IRepositoryCarro repositoryCarro;

    public CarroService(IRepositoryCarro repositoryCarro){
        this.repositoryCarro = repositoryCarro;
    }

    @Transactional
    public long save(Carro carro){
       Carro c =  repositoryCarro.save(carro);
        return c.getId();
    }

    public List<CarroDTO> findAll(){
        return repositoryCarro.findAll().stream().map(carro -> 
            getCarro(carro)        
        ).collect(Collectors.toList());   
    }
    public CarroDTO getCarro(Carro carro){
        return CarroDTO.builder()
            .id(carro.getId())
            .disponivel(carro.isDisponivel())
            .km(carro.getKm())
            .placa(carro.getPlaca())
            .modeloCarro(getModeloCarro(carro.getModelo()))
            .build();
    }

    public CarroAndModeloDTO getModeloCarro(Modelo modelo){
        return CarroAndModeloDTO.builder()
            .abs(modelo.isAbs())
            .air_bag(modelo.isAir_bag())
            .lugares(modelo.getLugares())
            .name(modelo.getName())
            .num_portas(modelo.getNum_portas()).build();
    }

    @Transactional
    public CarroDTO edit(long id, CarroDTO carro){

        Carro c = repositoryCarro.findById(id).orElseThrow(() ->
             new NoFindCarroException("Carro nao encontrado"));

        c.setDisponivel(carro.isDisponivel());
        c.setId(id);
        c.setKm(carro.getKm());
        c.setPlaca(carro.getPlaca());
        return getCarro(c);

    }

    @Transactional
    public long delete(long id){
        repositoryCarro.deleteById(id);
        return id;
    }
}
