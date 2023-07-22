package com.sgvcore.sevices;

import com.sgvcore.DTOs.distritoDTO.DistritoProvinciaDTO;
import com.sgvcore.DTOs.distritoDTO.DistritoRespostaDTO;
import com.sgvcore.Model.Distrito;
import com.sgvcore.Model.Provincia;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.DistritoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistritoService {
    @Autowired
    private DistritoRepo distritoRepo;

    public Distrito criar(Distrito model) {
        return distritoRepo.save(model);
    }

    public List<DistritoRespostaDTO> listarTodos() {
        return distritoRepo.findAll().stream().map(distrito -> new DistritoRespostaDTO(distrito)).collect(Collectors.toList());
    }

    public List<Distrito> listarTodosPorProvincia(Provincia idProvincia) {
        return distritoRepo.findByIdProvincia(idProvincia);
    }

    public Distrito buscarPorId(Long id) {
        return distritoRepo.findById(id).orElse(null);
    }

    public Distrito buscarDistritoPorIdEProvinciaId(Long id, Provincia idProvincia) {
        return distritoRepo.findByIdAndIdProvincia(id, idProvincia).orElse(null);
    }

    public DistritoProvinciaDTO buscarDistritoPorId(Long id) {
        return new DistritoProvinciaDTO(distritoRepo.findByDistritoId(id));
    }

    public Distrito buscarDistritoPorCodigoEProvincia(String codigo, Provincia provincia) throws ModelNotFound {
        return distritoRepo.findByCodigoAndIdProvincia(codigo, provincia).orElseThrow(() -> new ModelNotFound("Distrito nao encontrado"));
    }
}
