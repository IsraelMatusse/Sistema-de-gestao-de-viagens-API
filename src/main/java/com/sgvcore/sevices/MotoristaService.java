package com.sgvcore.sevices;

import com.sgvcore.DTOs.motoristDTOs.MotoristaRespostaDTO;
import com.sgvcore.Model.Motorista;
import com.sgvcore.repository.MotoristaRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepo motoristaRepo;

    public Motorista cirar(Motorista motorista){
       return motoristaRepo.save(motorista);
    }
    public List<MotoristaRespostaDTO>listar(){
        return motoristaRepo.findAll().stream().map(motorista -> new MotoristaRespostaDTO(motorista)).collect(Collectors.toList());
    }
    public MotoristaRespostaDTO buscarPorCodigoRes(String codigo){
        Motorista motorista=motoristaRepo.findByCodigo(codigo);
        if(motorista !=null){
            return new MotoristaRespostaDTO(motorista);
        }
        return null;
    }
    public Motorista buscarPorCodigo(String codigo){
        return motoristaRepo.findByCodigo(codigo);
    }
    public long numeroDeMotoristas(){
        return motoristaRepo.count();
    }
}
