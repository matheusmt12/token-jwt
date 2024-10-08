package com.example.thymeleaf.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dto.ModeloDTO;
import com.example.thymeleaf.entity.Carro;
import com.example.thymeleaf.entity.Modelo;
import com.example.thymeleaf.exceptions.NoFindModeloException;
import com.example.thymeleaf.repository.IRepositoryModelo;

import jakarta.transaction.Transactional;

@Service
public class ModeloService {


    @Autowired
    private final IRepositoryModelo repositoryModelo;

    public ModeloService(IRepositoryModelo repositoryModelo){
        this.repositoryModelo = repositoryModelo;
    }


    public List<ModeloDTO> findAll(){

        return repositoryModelo.findAll().stream().map(modelo -> 
            getModelo(modelo)).collect(Collectors.toList());
    }

    public ModeloDTO getModelo(Modelo modelo){

        return ModeloDTO.builder()
            .id(modelo.getId())
            .abs(modelo.isAbs())
            .air_bag(modelo.isAir_bag())
            .lugares(modelo.getLugares())
            .name(modelo.getName())
            .num_portas(modelo.getNum_portas())
            .carros(getCarros(modelo.getCarros()))
            .build();

    }

    public List<Carro> getCarros(List<Carro> carros){
        return carros.stream().map(carro ->{
            Carro c = new Carro();
            c.setId(carro.getId());
            c.setDisponivel(carro.isDisponivel());
            c.setKm(carro.getKm());
            c.setPlaca(carro.getPlaca());
            return c;
            
        }
        ).collect(Collectors.toList());
    }

    @Transactional
    public ModeloDTO edit(long id, ModeloDTO modelo){

        Modelo m = repositoryModelo.findById(id).orElseThrow(() -> 
            new NoFindModeloException("O modelo nao foi encontrado"));

        m.setAbs(modelo.isAbs());
        m.setAir_bag(modelo.isAir_bag());
        m.setId(id);
        m.setLugares(modelo.getLugares());
        m.setName(modelo.getName());
        m.setNum_portas(modelo.getNum_portas());
        repositoryModelo.save(m);
        return getModelo(m);
    }

    public long delete(long id){
        repositoryModelo.deleteById(id);
        return id;
    }
}
