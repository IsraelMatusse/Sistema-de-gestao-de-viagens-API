package com.sgvcore.sevices;

import com.sgvcore.Model.Provincia;
import com.sgvcore.repository.ProvinciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    ProvinciaRepo provinciaRepo;

    public Provincia criar(Provincia provincia){
        return provinciaRepo.save(provincia);
    }

    @Transactional(readOnly = true)
    public List<Provincia> listarTodos(){
        return provinciaRepo.findAll();
    }

    public Provincia buscarPorId(Long id){
        return provinciaRepo.findById(id).orElse(null);
    }

    public Provincia buscarProvinciaporCodigo(String codigo){
        return provinciaRepo.findByCodigo(codigo);
    }
}
