package com.sgvcore.sevices;

import com.sgvcore.DTOs.modeloDTOs.ModeloRespostaDTO;
import com.sgvcore.Model.Marca;
import com.sgvcore.Model.MarcaModelo;
import com.sgvcore.repository.MarcaModeloRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaModeloService {

    @Autowired
    private MarcaModeloRepo marcaModeloRepo;

    public MarcaModelo criar(MarcaModelo marcaModelo){
        return marcaModeloRepo.save(marcaModelo);
    }
    public List<MarcaModelo> listar(){
        return marcaModeloRepo.findAll();
    }
    public List<ModeloRespostaDTO> buscarModelosDeUmaMarca(Marca marca){
        return marcaModeloRepo.findByMarca(marca).stream().map(marcaModelo -> new ModeloRespostaDTO(marcaModelo.getModelo())).collect(Collectors.toList());
    }
}
