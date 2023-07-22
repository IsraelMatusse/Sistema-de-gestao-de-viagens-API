package com.sgvcore.sevices;

import com.sgvcore.DTOs.modeloDTOs.ModeloRespostaDTO;
import com.sgvcore.Model.Modelo;
import com.sgvcore.repository.ModeloRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertFalse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepo modeloRepo;

    public Modelo criar(Modelo modelo){
        return modeloRepo.save(modelo);
    }
    public List<Modelo> listar(){
        return modeloRepo.findAll();
    }
    public List<ModeloRespostaDTO>listarRes(){
        return modeloRepo.findAll().stream().map(modelo -> new ModeloRespostaDTO(modelo)).collect(Collectors.toList());
    }
    public Modelo buscarPorCodigo(String codigo){
        return modeloRepo.findByCodigo(codigo);
    }
    public ModeloRespostaDTO buscarPorCodigoRes(String codigo){
        Modelo modelo= modeloRepo.findByCodigo(codigo);
        if(modelo !=null){
            return new ModeloRespostaDTO(modelo);
        }
        return null;
    }
}
