package com.sgvcore.sevices;

import com.sgvcore.Model.Licenca;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.repository.LicencaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicencaService {

    @Autowired
    private LicencaRepo licencaRepo;

    public Licenca criar(Licenca licenca) {
        return licencaRepo.save(licenca);
    }

    public List<Licenca> listarLicencas() {
        return licencaRepo.findAll();
    }

    public Licenca buscarLicencaPorId(Long id) {
        return licencaRepo.findById(id).orElse(null);
    }

    public Licenca buscarPorNumeroLicenca(String numeroLicenca) throws ModelNotFound {
        return licencaRepo.findByNumeroLicenca(numeroLicenca).orElseThrow(() -> new ModelNotFound("Licenca nao encontrada"));
    }

    public Boolean existePorNumeroDeLicenca(String numeroLicenca) {
        return licencaRepo.existsByNumeroLicenca(numeroLicenca);
    }
}
