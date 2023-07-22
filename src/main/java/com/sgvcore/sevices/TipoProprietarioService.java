package com.sgvcore.sevices;

import com.sgvcore.DTOs.tipoProprietarioDTOs.TipoProprietarioRespostaDTO;
import com.sgvcore.Model.TipoProprietario;
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

    public TipoProprietarioRespostaDTO buscarPorDesignacao(String designacao) {
        TipoProprietario tipoProprietario = tipoProprietariRepo.findByDesignacao(designacao);
        if (tipoProprietario != null) {
        return new TipoProprietarioRespostaDTO(tipoProprietario);
        }
        return null;
    }

    public TipoProprietarioRespostaDTO buscarPorCodigoRes(String codigo){
        TipoProprietario tipoProprietario= tipoProprietariRepo.findByCodigo(codigo);
        if(tipoProprietario !=null){
            return new TipoProprietarioRespostaDTO(tipoProprietario);
        }
        return null;
    }

    public TipoProprietario buscarPorCodigo(String codigo){
        return tipoProprietariRepo.findByCodigo(codigo);
    }
}
