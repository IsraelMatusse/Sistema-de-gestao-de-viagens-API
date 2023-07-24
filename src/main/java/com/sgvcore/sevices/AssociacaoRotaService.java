package com.sgvcore.sevices;

import com.sgvcore.DTOs.rotaDTO.RotaRespostaDTO;
import com.sgvcore.Model.Associacao;
import com.sgvcore.Model.AssociacaoRota;
import com.sgvcore.Model.Rota;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.AssociacaoRotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociacaoRotaService {

    @Autowired
    private AssociacaoRotaRepo associacaoRotaRepo;

    public AssociacaoRota criar(AssociacaoRota associacaoRota) {
        return associacaoRotaRepo.save(associacaoRota);
    }

    public List<AssociacaoRota> listar() {
        return associacaoRotaRepo.findAll();
    }

    public AssociacaoRota buscarPorId(Long id) {
        return associacaoRotaRepo.findById(id).orElse(null);
    }

    public RotaRespostaDTO buscarRotasPeloCodigoDaAssociacaoEAssociacaoRes(Associacao associacao, String codigo) throws ModelNotFound {
        AssociacaoRota associacaoRota = associacaoRotaRepo.findByIdAssociacaoAndIdRotaCodigo(associacao, codigo).orElseThrow(() -> new ModelNotFound("Rota nao encontrada"));
        return new RotaRespostaDTO(associacaoRota.getIdRota());
    }

    public Rota buscarRotasPeloCodigoDaAssociacaoEAssociacao(Associacao associacao, String codigo) throws ModelNotFound {
        return associacaoRotaRepo.findByIdAssociacaoAndIdRotaCodigo(associacao, codigo).orElseThrow(() -> new ModelNotFound("Rota naoe encontrada")).getIdRota();
    }

    public List<RotaRespostaDTO> buscarPorAssociacao(Associacao associacao) {
        return associacaoRotaRepo.findByIdAssociacao(associacao).stream().map(associacaoRota -> new RotaRespostaDTO(associacaoRota.getIdRota())).collect(Collectors.toList());
    }
}

