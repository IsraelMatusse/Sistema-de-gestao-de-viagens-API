package com.sgvcore.sevices;

import com.sgvcore.Model.AssociacaoRota;
import com.sgvcore.repository.AssociacaoRotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}

