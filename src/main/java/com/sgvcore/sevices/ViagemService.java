package com.sgvcore.sevices;

import com.sgvcore.DTOs.viagemDTO.ViagemRespostaDTO;
import com.sgvcore.DTOs.viajanteDTO.ViajanteRespostaDTO;
import com.sgvcore.Model.Viagem;
import com.sgvcore.repository.ViagemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepo viagemRepo;

    public Viagem criar(Viagem viagem){
        return  viagemRepo.save(viagem);
    }
    public List<ViagemRespostaDTO> listar(){
        return viagemRepo.findAll().stream().map(viagem -> new ViagemRespostaDTO(viagem)).collect(Collectors.toList());
    }
    public Viagem buscarPorId(Long id){
        return viagemRepo.findById(id).orElse(null);
    }
    public Viagem buscarPorCodigo (String codigo){
       return viagemRepo.findByCodigo(codigo);
    }
    public ViagemRespostaDTO buscarPorCodigoRes(String codigo){
        Viagem viagem=viagemRepo.findByCodigo(codigo);
        if(viagem!=null){
            return new ViagemRespostaDTO(viagem);
        }
        return null;}
    public Long numeroViagens(){
        return viagemRepo.count();
    }

}
