package com.sgvcore.sevices;

import com.sgvcore.DTOs.corDTOs.CorRespostaDTO;
import com.sgvcore.Model.Cor;
import com.sgvcore.repository.CorRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorService {

    @Autowired
    private CorRepo corRepo;

    public Cor criar(Cor cor){
        return corRepo.save(cor);
    }
    public List<CorRespostaDTO> listar(){
        return corRepo.findAll().stream().map(cor -> new CorRespostaDTO(cor)).collect(Collectors.toList());
    }
    public Cor buscarPorId(Long id){
        return corRepo.findById(id).orElse(null);
    }
    public CorRespostaDTO buscarPorIdRes(Long id){
        Cor cor= corRepo.findById(id).orElse(null);
        if(cor !=null){
            return new CorRespostaDTO(cor);
        }
        return null;
    }

}
