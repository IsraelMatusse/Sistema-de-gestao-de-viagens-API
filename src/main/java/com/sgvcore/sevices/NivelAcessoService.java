package com.sgvcore.sevices;

import com.sgvcore.Model.NivelAcesso;
import com.sgvcore.repository.NivelAcessoRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NivelAcessoService {

    @Autowired
    private NivelAcessoRepo nivelAcessoRepo;

    public NivelAcesso criarNivelDeAcesso(NivelAcesso nivelAcesso){
        return nivelAcessoRepo.save(nivelAcesso);
    }

    public List<NivelAcesso> listarTodosNiveisDeAcesso(){
        return nivelAcessoRepo.findAll();
    }

}
