package com.sgvcore.sevices;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoRespostaDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.repository.AssociacaoRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociacaoService {

    @Autowired
    private AssociacaoRepo associacaoRepo;

    public Associacao criar(Associacao associacao){
        return associacaoRepo.save(associacao);
    }
    public List<AssociacaoRespostaDTO> listarAssociacao(){
        return associacaoRepo.findAll().stream().map(associacao -> new AssociacaoRespostaDTO(associacao)).collect(Collectors.toList());
    }
    public Associacao buscarPorCodigo(String codigo){
        return associacaoRepo.findByCodigo(codigo);
    }

    public AssociacaoRespostaDTO buscarPorCodigoRes(String codigo){
        Associacao associacao=associacaoRepo.findByCodigo(codigo);
        if(associacao !=null){
            return new AssociacaoRespostaDTO(associacao);
        }
        return null;
    }




}
