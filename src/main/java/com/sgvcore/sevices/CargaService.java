package com.sgvcore.sevices;

import com.sgvcore.DTOs.cargaCriarDTO.CargaRespostaDTO;
import com.sgvcore.Model.Carga;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.repository.CargaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargaService {

    @Autowired
    private CargaRepo cargaRepo;

    public Carga criar(Carga carga) {
        return cargaRepo.save(carga);
    }

    public List<CargaRespostaDTO> listarCargas() {
        return cargaRepo.findAll().stream().map(carga -> new CargaRespostaDTO(carga)).collect(Collectors.toList());
    }

    public Carga buscarCargaPorCodigo(String codigoCarga) throws ContentAlreadyExists {
        return cargaRepo.findByCodigoCarga(codigoCarga).orElseThrow(() -> new ContentAlreadyExists("Carga ja existe"));
    }

    public Carga buscarCargaPorDesignacao(String designacaoCarga) throws ContentAlreadyExists {
        return cargaRepo.findByDesignacao(designacaoCarga).orElseThrow(() -> new ContentAlreadyExists("Carga ja existe"));
    }
}
