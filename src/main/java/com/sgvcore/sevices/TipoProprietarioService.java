package com.sgvcore.sevices;

import com.sgvcore.DTOs.tipoProprietarioDTOs.TipoProprietarioRespostaDTO;
import com.sgvcore.Model.TipoProprietario;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.TipoProprietariRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoProprietarioService {

    @Autowired
    private TipoProprietariRepo tipoProprietariRepo;

    public TipoProprietario criar(TipoProprietario tipoProprietario) {
        return tipoProprietariRepo.save(tipoProprietario);
    }

    public List<TipoProprietarioRespostaDTO> listar() {
        return tipoProprietariRepo.findAll().stream().map(tipoProprietario -> new TipoProprietarioRespostaDTO(tipoProprietario)).collect(Collectors.toList());
    }

    public TipoProprietarioRespostaDTO buscarPorDesignacao(String designacao) throws ModelNotFound {
        TipoProprietario tipoProprietario = tipoProprietariRepo.findByDesignacao(designacao).orElseThrow(() -> new ModelNotFound("Tipo de proprietario nao encontrado"));
        return new TipoProprietarioRespostaDTO(tipoProprietario);
    }

    public TipoProprietarioRespostaDTO buscarPorCodigoRes(String codigo) throws ModelNotFound {
        TipoProprietario tipoProprietario = tipoProprietariRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Tipo de proprietario nao encontrado"));
        return new TipoProprietarioRespostaDTO(tipoProprietario);
    }

    public TipoProprietario buscarPorCodigo(String codigo) throws ModelNotFound {
        return tipoProprietariRepo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFound("Tipo de proprietario nao encontrado"));
    }
}
