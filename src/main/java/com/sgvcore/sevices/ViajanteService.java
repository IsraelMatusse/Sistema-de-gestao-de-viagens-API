package com.sgvcore.sevices;

import com.sgvcore.DTOs.viajanteDTO.ViajanteRespostaDTO;
import com.sgvcore.Model.Viajante;
import com.sgvcore.repository.ViajanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajanteService {

    @Autowired
    private ViajanteRepo viajanteRepo;

    public Viajante criar(Viajante viajante){
        return viajanteRepo.save(viajante);
    }
    public  List<ViajanteRespostaDTO> listarViajantes(){
        return viajanteRepo.findAll().stream().map(viajante -> new ViajanteRespostaDTO(viajante)).collect(Collectors.toList());
    }

    public ViajanteRespostaDTO buscarViajantPorId(Long id){
        Viajante viajante= viajanteRepo.findById(id).orElse(null);
        if(viajante!=null){
            return new ViajanteRespostaDTO(viajante);
        }
        return null;
    }
}
