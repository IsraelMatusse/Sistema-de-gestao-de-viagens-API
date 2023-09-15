package com.sgvcore.sevices;

import com.sgvcore.DTOs.generoDTO.GeneroRespostaDTO;
import com.sgvcore.Model.Genero;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.GeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepo generoRepo;

    public void criarGenero(Genero genero) {
        generoRepo.save(genero);
    }

    public List<GeneroRespostaDTO> listarGeneros() {
        return generoRepo.findAll().stream().map(genero -> new GeneroRespostaDTO(genero)).collect(Collectors.toList());
    }

    public Genero buscarPorId(Long id) throws ModelNotFound {
        return generoRepo.findById(id).orElseThrow(() -> new ModelNotFound("Genero nao encontrado"));
    }
}
