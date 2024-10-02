package com.example.thymeleaf.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dto.MarcaDTO;
import com.example.thymeleaf.entity.Marca;
import com.example.thymeleaf.exceptions.NoFindMarcaException;
import com.example.thymeleaf.repository.IRepositoryMarca;
import java.lang.Object;
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
}
