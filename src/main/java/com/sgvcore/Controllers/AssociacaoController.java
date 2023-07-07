package com.sgvcore.Controllers;

import com.sgvcore.DTOs.associacaoDTOs.AssociacaoCriarDTOs;
import com.sgvcore.Model.*;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

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
    @Autowired
    private RotaService rotaService;
    @Autowired
    private AsociacaoRotaService associacaoRotaService;
    @Autowired
    private ViaturaService viaturaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> crarAssociacoes(@RequestBody AssociacaoCriarDTOs dto) {
        Contacto contacto = contactoService.buscarContactoPorMsisdn(dto.getMsdisn());
        Contacto novoContacto;
        System.out.println(dto.getMsdisn());
        if (contacto == null) {
            novoContacto = new Contacto(dto);
            System.out.println(novoContacto);
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, contacto ja existe!", null));
        }
        TipopLicenca tipopLicenca = tipoLicencaService.buscarTipoLicencaPorId(dto.getTipoLicenca());
        System.out.println(dto.getTipoLicenca());
        if (tipopLicenca == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de licenca nao encontrado!", null));
        }
        Licenca licenca = licencaService.buscarPorNumeroLicenca(dto.getNumeroLicenca());
        System.out.println(dto.getNumeroLicenca());
        Licenca novaLicenca;
        if (licenca == null) {
            novaLicenca = new Licenca(dto, tipopLicenca);
            System.out.println(novaLicenca);
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Licenca ja existe!", null));
        }
        try {
            licencaService.criar(novaLicenca);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        try {
            contactoService.criar(novoContacto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        try{
            if(!dto.getRotas().isEmpty()){
                for(int i=0; i<dto.getRotas().size(); i++){
                    Boolean existe=rotaService.verificarAexistenciaDaRotaPorDesignacao(dto.getRotas().get(i));
                    if(!existe){
                        return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Rota nao encontrada!", null));
                    }
                }
                Associacao associacao = associacaoService.criar(new Associacao(dto, novoContacto, novaLicenca));
                for(int i=0; i<dto.getRotas().size(); i++){
                    Rota rota=rotaService.buscarRotaPorDesignacao(dto.getRotas().get(i));
                    AssociacaoRota novaAssociacaoRota= new AssociacaoRota(rota, associacao);
                    associacaoRotaService.criar(novaAssociacaoRota);
                }

            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Associacao cadastrada com sucesso", null));
    }

    @GetMapping("/{codigo_associacao}/viaturas")
    public ResponseEntity<ResponseAPI>listarViaturasAssociacao(@PathVariable(value = "codigo_associacao")String codigoAssociacao){
        Associacao associacao=associacaoService.buscarPorCodigo(codigoAssociacao);
        if(associacao==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Associacao nao encontrada!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Viaturas da associacao"+associacao.getDesignacao()+ " ", viaturaService.buscarViaturasDaDaAssociacao(associacao)));

    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listarAssociacoes() {
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Associacoes do sistema", associacaoService.listarAssociacao()));
    }
    @GetMapping("/{codigo-associacao}")
    public ResponseEntity<ResponseAPI>buscarPorCodigo(@PathVariable(value = "codigo-associacao") String codigoAssociacao){
        Associacao associacao= associacaoService.buscarPorCodigo(codigoAssociacao);
        if(associacao ==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de licenca nao encontrado!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Associacoes do sistema", associacaoService.buscarPorCodigoRes(codigoAssociacao)));

    }
}
