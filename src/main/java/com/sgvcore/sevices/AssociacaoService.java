package com.sgvcore.sevices;

import com.sgvcore.Model.Associacao;
import com.sgvcore.repository.AssociacaoRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociacaoService {

    @Autowired
    private AssociacaoRepo associacaoRepo;

    public Associacao criar(Associacao associacao){
        return associacaoRepo.save(associacao);
    }
    public List<Associacao> listarAssociacao(){
        return associacaoRepo.findAll();
    }
    public Associacao buscarPorCodigo(String codigo){
        return associacaoRepo.findByCodigo(codigo);
    }





}
