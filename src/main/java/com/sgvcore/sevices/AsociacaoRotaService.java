package com.sgvcore.sevices;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoRespostaDTO;
import com.sgvcore.DTOs.rotaDTO.RotaRespostaDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.AssociacaoRota;
import com.sgvcore.Model.Rota;
import com.sgvcore.repository.AssociacaoRotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsociacaoRotaService {

    @Autowired
    private AssociacaoRotaRepo associacaoRotaRepo;

     public AssociacaoRota criar(AssociacaoRota associacaoRota){
         return associacaoRotaRepo.save(associacaoRota);
     }
     public List<AssociacaoRota> listar(){
         return associacaoRotaRepo.findAll();
     }
     public AssociacaoRota buscarPorId(Long id){
         return associacaoRotaRepo.findById(id).orElse(null);
     }

     public RotaRespostaDTO buscarRotasPeloCodigoDaAssociacaoEAssociacaoRes(Associacao associacao, String codigo){
         AssociacaoRota associacaoRota= associacaoRotaRepo.findByIdAssociacaoAndIdRotaCodigo(associacao, codigo);
         if(associacaoRota !=null){
             return new RotaRespostaDTO(associacaoRota.getIdRota());
         }
         return null;
     }
     public Rota buscarRotasPeloCodigoDaAssociacaoEAssociacao(Associacao associacao, String codigo){
         return associacaoRotaRepo.findByIdAssociacaoAndIdRotaCodigo(associacao, codigo).getIdRota();
     }
     public List<RotaRespostaDTO> buscarPorAssociacao(Associacao associacao){
     return associacaoRotaRepo.findByIdAssociacao(associacao).stream().map(associacaoRota ->new RotaRespostaDTO(associacaoRota.getIdRota())).collect(Collectors.toList());
         }
}

