package com.sgvcore.Controllers;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoCriarDTOs;
import com.sgvcore.Model.*;
import com.sgvcore.sevices.AssociacaoService;
import com.sgvcore.sevices.ContactoService;
import com.sgvcore.sevices.LicencaService;
import com.sgvcore.sevices.TipoLicencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/associacoes")
public class AssociacaoController {
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private LicencaService licencaService;
    @Autowired
    private TipoLicencaService tipoLicencaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> crarAssociacoes(@RequestBody AssociacaoCriarDTOs dto) {
        Contacto contacto = contactoService.buscarContactoPorMsisdn(dto.getMsdisn());
        Contacto novoContacto = null;
        System.out.println(dto.getMsdisn());
        if (contacto == null) {
            try {
                novoContacto = contactoService.criar(new Contacto(dto));
                System.out.println(novoContacto);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
            }
        }else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, contacto ja existe!", null));
        }
        TipopLicenca tipopLicenca = tipoLicencaService.buscarTipoLicencaPorId(dto.getTipoLicenca());
        System.out.println(dto.getTipoLicenca());
        if (tipopLicenca == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de licenca nao encontrado!", null));
        }
        Licenca licenca = licencaService.buscarPorNumeroLicenca(dto.getNumeroLicenca());
        System.out.println(dto.getNumeroLicenca());
        Licenca novaLicenca = null;
        if (licenca == null) {
            try {
                novaLicenca = licencaService.criar(new Licenca(dto, tipopLicenca));
                System.out.println(novaLicenca);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
            }
        }else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Licenca ja existe!", null));
        }
        try{
        Associacao associacao= associacaoService.criar(new Associacao(dto, novoContacto, novaLicenca));
    }catch (Exception e){
        return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
    }
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Associacao cadastrada com sucesso", null));
    }
}
