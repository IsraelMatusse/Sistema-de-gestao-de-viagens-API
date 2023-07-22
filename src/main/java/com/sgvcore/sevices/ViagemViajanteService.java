package com.sgvcore.sevices;

import com.sgvcore.DTOs.viajanteDTO.ViajanteRespostaDTO;
import com.sgvcore.Model.Viagem;
import com.sgvcore.Model.ViagemViajante;
import com.sgvcore.Model.Viajante;
import com.sgvcore.repository.ViagemViajanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemViajanteService {

    @Autowired
    private ViagemViajanteRepo viagemViajanteRepo;

    public ViagemViajante crir(ViagemViajante viagemViajante){
        return viagemViajanteRepo.save(viagemViajante);
    }
    public List<ViagemViajante> listar(){
        return viagemViajanteRepo.findAll();
    }

    public ViagemViajante buscarPorId(Long id){
        return viagemViajanteRepo.findById(id).orElse(null);
    }

    public List<ViajanteRespostaDTO> listarViajantesDeUmaViagem(Viagem viagem){
      return   viagemViajanteRepo.findByIdViagem(viagem).stream().map(viagemViajante -> new ViajanteRespostaDTO(viagemViajante.getIdViajante())).collect(Collectors.toList());
    }
    public Long numeroViagens(Viagem viagem) {
      return   viagemViajanteRepo.countByIdViagem(viagem);
    }
}
