package com.sgvcore.sevices;

import com.sgvcore.Model.Viagem;
import com.sgvcore.repository.ViagemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepo viagemRepo;

    public Viagem criar(Viagem viagem){
        return  viagemRepo.save(viagem);
    }
    public List<Viagem> listar(){
        return viagemRepo.findAll();
    }
    public Viagem buscarPorId(Long id){
        return viagemRepo.findById(id).orElse(null);
    }
}
