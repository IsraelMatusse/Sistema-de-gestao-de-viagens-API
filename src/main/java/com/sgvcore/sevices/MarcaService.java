package com.sgvcore.sevices;

import com.sgvcore.DTOs.marcaDTOs.MarcaRespostaDTO;
import com.sgvcore.Model.Marca;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.MarcaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepo marcaRepo;

    public Marca criar(Marca marca) {
        return marcaRepo.save(marca);
    }

    public List<Marca> listar() {
        return marcaRepo.findAll();
    }

    public List<MarcaRespostaDTO> listarRes() {
        return marcaRepo.findAll().stream().map(marca -> {
            try {
                return new MarcaRespostaDTO(marca);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Marca buscarPorCodigo(String codigo) throws ModelNotFound {
        return marcaRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Marca nao encontrada"));
    }

    public MarcaRespostaDTO buscarPorCodigoRes(String codigo) throws NoSuchAlgorithmException, ModelNotFound {
        Marca marca = marcaRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Marca nao encontrada"));
        return new MarcaRespostaDTO(marca);
    }
}
