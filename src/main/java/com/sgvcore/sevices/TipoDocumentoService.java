package com.sgvcore.sevices;

import com.sgvcore.DTOs.tipoDocumentoDTO.TipoDocumentoRespostaDTO;
import com.sgvcore.Model.TipoDocumentoIdentificacao;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.TipoDocumentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepo tipoDocumentoIdentificacaoRepo;

    public TipoDocumentoIdentificacao criar(TipoDocumentoIdentificacao tipoDocumentoIdentificacao) {
        return tipoDocumentoIdentificacaoRepo.save(tipoDocumentoIdentificacao);
    }

    public List<TipoDocumentoRespostaDTO> listarTiposDocumento() {
        return tipoDocumentoIdentificacaoRepo.findAll().stream().map(tipoDocumentoIdentificacao1 -> new TipoDocumentoRespostaDTO(tipoDocumentoIdentificacao1)).collect(Collectors.toList());
    }

    public TipoDocumentoIdentificacao buscarTipoDocumentoporId(Long id) throws ModelNotFound {
        return tipoDocumentoIdentificacaoRepo.findById(id).orElse(null);
    }

    public TipoDocumentoIdentificacao buscarPorDesignacao(String designacao) throws ModelNotFound {
        return tipoDocumentoIdentificacaoRepo.findByDesignacao(designacao).orElseThrow(() -> new ModelNotFound("Tipo de documento nao encontrado"));
    }

    public TipoDocumentoIdentificacao buscarPorCodigo(String codigo){
        return tipoDocumentoIdentificacaoRepo.findByCodigo(codigo);
    }

    public boolean existePorId(Long id) {
        return tipoDocumentoIdentificacaoRepo.existsById(id);
    }
}
