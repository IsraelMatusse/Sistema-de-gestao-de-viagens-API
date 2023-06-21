package com.sgvcore.sevices;

import com.sgvcore.Model.ViagemViajante;
import com.sgvcore.repository.ViagemViajanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViagemViajanteService {

    @Autowired
    private ViagemViajanteRepo viagemViajanteRepo;

    public ViagemViajante crir(ViagemViajante viagemViajante){
        return viagemViajanteRepo.save(viagemViajante);
    }
    public List<ViagemViajante> listar(){
        return viagemViajanteRepo.findAll();
    }

    public ViagemViajante buscarPorId(Long id){
        return viagemViajanteRepo.findById(id).orElse(null);
    }
}
