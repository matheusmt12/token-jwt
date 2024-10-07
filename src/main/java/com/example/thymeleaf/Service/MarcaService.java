package com.example.thymeleaf.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dto.MarcaDTO;
import com.example.thymeleaf.dto.ModeloDTO;
import com.example.thymeleaf.entity.Marca;
import com.example.thymeleaf.entity.Modelo;
import com.example.thymeleaf.exceptions.NoFindMarcaException;
import com.example.thymeleaf.repository.IRepositoryMarca;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

@Service
public class MarcaService {

    @Autowired
    private final IRepositoryMarca repositoryMarca;

    public MarcaService(IRepositoryMarca repositoryMarca ){
        this.repositoryMarca = repositoryMarca;
    }

    @Transactional
    public long save(MarcaDTO marcaDto){

        Marca marca = new Marca();

        marca.setId(marcaDto.getId());
        marca.setName(marcaDto.getName());
        repositoryMarca.save(marca);

        return marca.getId();
    }

    public MarcaDTO getMarca(long id){

       Marca marca = repositoryMarca.findById(id).orElseThrow(() -> new NoFindMarcaException("Marca não encontrada"));
       return MarcaDTO.builder()
        .id(marca.getId())
        .name(marca.getName()).build();

    }

    public List<MarcaDTO> findAll(){

        return repositoryMarca.findAll().stream().map(marcas -> 
            getMarcaDTO(marcas)).collect(Collectors.toList());

    }

    public MarcaDTO getMarcaDTO(Marca marca){
        return MarcaDTO.builder()
            .id(marca.getId())
            .name(marca.getName())
            .modelos(getModelos(marca.getModelos()))
            .build();
    }

    public List<ModeloDTO> getModelos(List<Modelo> modelos){
        return modelos.stream().map(m ->
            ModeloDTO.builder()
            .id(m.getId())
            .abs(m.isAbs())
            .lugares(m.getLugares())
            .name(m.getName())
            .num_portas(m.getNum_portas()) 
            .air_bag(m.isAir_bag())
            .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public MarcaDTO put(long id, MarcaDTO marca){

       Marca m = repositoryMarca.findById(id).orElseThrow(
            () -> new NoFindMarcaException("Marca nao encontrada"));
        m.setId(id);
        m.setName(marca.getName());
        repositoryMarca.save(m);
        marca.setId(id);
        return marca;
    }

    @Transactional
    public long delete(long id){
        repositoryMarca.deleteById(id);
        return id;
    }
}
