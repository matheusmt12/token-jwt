package com.example.thymeleaf.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        locacao.setKm_inicial(c.getKm());

        return repositoryLocacao.save(locacao).getId();
    }
    

}
