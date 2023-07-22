package com.sgvcore.sevices;

import com.sgvcore.Model.Genero;
import com.sgvcore.repository.GeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepo generoRepo;
    public void criarGenero (Genero genero){
        generoRepo.save(genero);
    }

    public List<Genero> listarGeneros(){
        return generoRepo.findAll();
    }

    public Genero buscarPorId(Long id){
        return generoRepo.findById(id).orElse(null);
    }
}
