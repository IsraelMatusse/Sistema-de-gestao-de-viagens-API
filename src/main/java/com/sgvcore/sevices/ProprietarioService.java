package com.sgvcore.sevices;

import com.sgvcore.Model.Proprietario;
import com.sgvcore.repository.ProprietarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepo proprietarioRepo;

    public Proprietario criar(Proprietario proprietario){
        return proprietarioRepo.save(proprietario);
    }
    public List<Proprietario> listar(){
        return proprietarioRepo.findAll();
    }
    public Proprietario buscarPorId(Long id){
        return proprietarioRepo.findById(id).orElse(null);
    }
    public Proprietario buscarPorCodigo(String codigo){
        return proprietarioRepo.findByCodigo(codigo);
    }
    public Long numeroProprietarios(){return proprietarioRepo.count();}
}
