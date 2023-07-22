package com.sgvcore.sevices;

import com.sgvcore.DTOs.rotaDTO.RotaRespostaDTO;
import com.sgvcore.Model.Rota;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.RotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RotaService {

    @Autowired
    private RotaRepo rotaRepo;

    public Rota criar(Rota rota) {
        return rotaRepo.save(rota);
    }

    public List<RotaRespostaDTO> listarRotas() {
        return rotaRepo.findAll().stream().map(rota -> new RotaRespostaDTO(rota)).collect(Collectors.toList());
    }

    public Optional<Rota> buscarRotaPorId(Long id) {
        return rotaRepo.findById(id);
    }

    public Rota buscarPorCodigo(String codigoRota) throws ModelNotFound {
        return rotaRepo.findByCodigo(codigoRota).orElseThrow(() -> new ModelNotFound("Rota nao encontrada!"));
    }

    public Boolean verificarAexistenciaDaRotaPorDesignacao(String rota) {
        return rotaRepo.existsByNomerota(rota);
    }

    public Rota buscarRotaPorDesignacao(String designacao) throws ModelNotFound {
        return rotaRepo.findByNomerota(designacao).orElseThrow(() -> new ModelNotFound("Rota nao encontrada!"));
    }

    public Long numeroRotas() {
        return rotaRepo.count();
    }
}
