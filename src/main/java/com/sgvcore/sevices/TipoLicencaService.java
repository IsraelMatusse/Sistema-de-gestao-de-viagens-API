package com.sgvcore.sevices;

import com.sgvcore.DTOs.tipoLicencaDTOs.TipoLicencaRespostaDTO;
import com.sgvcore.Model.TipopLicenca;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.TipoLicencaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoLicencaService {

    @Autowired
    private TipoLicencaRepo tipoLicencaRepo;

    public TipopLicenca criar(TipopLicenca tipopLicenca) {
        return tipoLicencaRepo.save(tipopLicenca);
    }

    public List<TipopLicenca> listarTpoLicencas() {
        return tipoLicencaRepo.findAll();
    }

    public TipopLicenca buscarTipoLicencaPorId(Long id) throws ModelNotFound {
        return tipoLicencaRepo.findById(id).orElseThrow(() -> new ModelNotFound("Tipo de licenca nao encontrada"));
    }

    public List<TipoLicencaRespostaDTO>listarTiposLicencaResposta(){
        return tipoLicencaRepo.findAll().stream().map(tipopLicenca -> new TipoLicencaRespostaDTO(tipopLicenca)).collect(Collectors.toList());
    }

}
