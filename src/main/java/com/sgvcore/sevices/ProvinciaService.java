package com.sgvcore.sevices;

import com.sgvcore.Model.Provincia;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.ProvinciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    ProvinciaRepo provinciaRepo;

    public Provincia criar(Provincia provincia) {
        return provinciaRepo.save(provincia);
    }

    @Transactional(readOnly = true)
    public List<Provincia> listarTodos() {
        return provinciaRepo.findAll();
    }

    public Provincia buscarPorId(Long id) throws ModelNotFound {
        return provinciaRepo.findById(id).orElseThrow(() -> new ModelNotFound("Provincia nao encontrada"));
    }

    public Provincia buscarProvinciaporCodigo(String codigo) throws ModelNotFound {
        return provinciaRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Provincia nao encontrada"));
    }
}
