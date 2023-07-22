package com.sgvcore.sevices;

import com.sgvcore.Model.Motorista;
import com.sgvcore.Model.MotoristaViactura;
import com.sgvcore.Model.Viactura;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.MotoristViacturaRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaViacturaService {

    @Autowired
    private MotoristViacturaRepo motoristViacturaRepo;

    public MotoristaViactura criar(MotoristaViactura motoristaViactura){
        return motoristViacturaRepo.save(motoristaViactura);
    }
    public List<MotoristaViactura>listar(){
        return motoristViacturaRepo.findAll();
    }
    public MotoristaViactura buscarPorCodigo(String codigo){
        return motoristViacturaRepo.findByCodigo(codigo);
    }
    public Motorista buscarMotoristaPeloCodigoMotoristaEViatura(Viactura viactura, String codigoMotorista) throws ModelNotFound {
        Motorista motorista= motoristViacturaRepo.findByIdViacturaAndIdMotoristaCodigo(viactura, codigoMotorista).getIdMotorista();
        if(motorista !=null){
            return motorista;
        }
        throw  new ModelNotFound("Motorista nao encontrado ou nao e desta viatura");
    }



}
