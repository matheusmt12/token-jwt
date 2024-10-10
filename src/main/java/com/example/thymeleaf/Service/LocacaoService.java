package com.example.thymeleaf.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dto.LocacaoDTO;
import com.example.thymeleaf.entity.Carro;
import com.example.thymeleaf.entity.Cliente;
import com.example.thymeleaf.entity.Locacoes;
import com.example.thymeleaf.exceptions.CarroIndisponivelException;
import com.example.thymeleaf.exceptions.NoActiveClienteException;
import com.example.thymeleaf.exceptions.NoFindCarroException;
import com.example.thymeleaf.exceptions.NoFindClienteException;
import com.example.thymeleaf.repository.IRepositoryCarro;
import com.example.thymeleaf.repository.IRepositoryCliente;
import com.example.thymeleaf.repository.IRepositoryLocacao;

import jakarta.transaction.Transactional;

@Service
public class LocacaoService {

    @Autowired
    private final IRepositoryLocacao repositoryLocacao;
    @Autowired
    private final IRepositoryCarro repositoryCarro;
    @Autowired
    private final IRepositoryCliente repositoryCliente;
    public LocacaoService(IRepositoryLocacao repositoryLocacao,IRepositoryCarro 
            repositoryCarro, IRepositoryCliente repositoryCliente){
        
            this.repositoryLocacao = repositoryLocacao;
            this.repositoryCarro = repositoryCarro;
            this.repositoryCliente = repositoryCliente;
    }

    @Transactional
    public long save(Locacoes locacao){

        Carro c = repositoryCarro.findById(locacao.getCarro().getId()).orElseThrow(
            ()-> new NoFindCarroException("Carro não encontrado"));
        Cliente cl = repositoryCliente.findById(locacao.getCliente().getId()).orElseThrow(
            () -> new NoFindClienteException("Cliente não encontrado"));

        if(!c.isDisponivel()){
            throw new CarroIndisponivelException("Carro indisponivel");
        }
        if(!cl.isActive()){
            throw new NoActiveClienteException("O cliente não esta ativo, não sera possivel fazer a locacão");
        }

        c.setDisponivel(false);
        repositoryCarro.save(c);
        locacao.setCarro(c);
        locacao.setData_inicio_locacao(LocalDateTime.now());
        locacao.setFinalizada(false);
        locacao.setKm_inicial(c.getKm());
        return repositoryLocacao.save(locacao).getId();
    }


    public List<LocacaoDTO> fidAll(){

        return repositoryLocacao.findAll().stream().map(locacao ->
            getLocacao(locacao)    
        ).collect(Collectors.toList());
    }

    public LocacaoDTO getLocacao(Locacoes locacao){
        return LocacaoDTO.builder()
            .codigo(locacao.getId())
            .dataFim(locacao.getData_fim_locacao())
            .dataLocacao(locacao.getData_inicio_locacao())
            .dataPrevista(locacao.getData_fim_locacao_previsto())
            .kmFinal(locacao.getKm_final())
            .kmInicial(locacao.getKm_inicial())
            .nameCarro(locacao.getCarro().getModelo().getName())
            .nameMarca(locacao.getCarro().getModelo().getMarca().getName())
            .nameCliente(locacao.getCliente().getName())
            .finalizada(locacao.isFinalizada())
            .build();
    }


}
