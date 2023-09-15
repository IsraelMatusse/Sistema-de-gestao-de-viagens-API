package com.sgvcore.sevices;

import com.sgvcore.DTOs.provinciaDTOs.ProvinciaRespostaDTO;
import com.sgvcore.Model.Provincia;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.ProvinciaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvinciaService {

private final  ProvinciaRepo provinciaRepo;

    public Provincia criar(Provincia provincia) {
        return provinciaRepo.save(provincia);
    }

    @Transactional(readOnly = true)
    public List<ProvinciaRespostaDTO> listarTodos() {
        return provinciaRepo.findAll().stream().map(provincia -> new ProvinciaRespostaDTO(provincia)).collect(Collectors.toList());
    }

    public Provincia buscarPorId(Long id) throws ModelNotFound {
        return provinciaRepo.findById(id).orElseThrow(() -> new ModelNotFound("Provincia nao encontrada"));
    }

    public Provincia buscarProvinciaporCodigo(String codigo) throws ModelNotFound {
        return provinciaRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Provincia nao encontrada"));
    }
}
