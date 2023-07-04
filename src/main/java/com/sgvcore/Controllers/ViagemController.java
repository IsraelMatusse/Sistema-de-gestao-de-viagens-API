package com.sgvcore.Controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;
    @Autowired
    private ViajanteService viajanteService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private ViagemViajanteService viagemViajanteService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    TipoDocumentoService tipoDocumentoService;
    @Autowired
    DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private DistritoService distritoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private CargaService cargaService;


    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViagem(@RequestBody @Valid ViagemCriarDTO dto) throws NoSuchAlgorithmException {
        Rota rota = rotaService.buscarRotaPorId(dto.getIdRota()).orElse(null);
        if (rota == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Rota nao encontrada!", null));
        }
        Associacao associacao = associacaoService.buscarPorCodigo(dto.getCodigoAssociacao());
        if (associacao == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Associacao nao enocntrada!", null));
        }
        try {
            viagemService.criar(new Viagem(dto, rota, associacao));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viajante criado com sucesso!", null));
    }

    @PostMapping("/associar-viajante")
    public ResponseEntity<ResponseAPI> associarViajante(@RequestBody ViagemAssociarViajanteDTO dto) {
        Viagem viagem = viagemService.buscarPorCodigo(dto.getCodigoViagem());
        if (viagem == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Viagem nao enocntrada!", null));
        }
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        if (genero == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Genero nao encontrado!", null));
        }
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        if (provincia == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Provincia nao encontrada!", null));
        }
        Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
        if (distrito == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Distrito nao encontrado!", null));
        }
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
        if (tipoDocumentoIdentificacao == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de documento nao encontrado!", null));
        }
        DocumentoIdentifiacacao documentoIdentifiacacao = documentoIdentificacaoService.buscarPorNumeroDocumento(dto.getNumeroDocumento());
        DocumentoIdentifiacacao novoDocumento = null;
        if (documentoIdentifiacacao == null) {
            novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Documento ja existe!", null));
        }
        Contacto contacto = contactoService.buscarContactoPorMsisdn(dto.getMsisdn());
        Contacto novoContacto = null;
        if (contacto == null) {
            novoContacto = new Contacto(dto);
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Contacto ja existe!", null));
        }
        Carga carga = cargaService.buscarCargaPorDesignacao(dto.getDesignacao());
        Carga novaCarga = null;
        if (carga == null) {
            novaCarga = new Carga(dto);
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Contacto ja existe!", null));
        }
        try {
            documentoIdentificacaoService.criar(novoDocumento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        try {
            contactoService.criar(novoContacto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        try {
            cargaService.criar(novaCarga);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        try {
            Viajante viajante = new Viajante(dto, genero, novaCarga, novoDocumento, provincia, distrito, novoContacto);
            viajanteService.criar(viajante);
            ViagemViajante viagemViajante = new ViagemViajante(viagem, viajante);
            viagemViajanteService.crir(viagemViajante);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viajante criado com sucesso!", null));


    }

}
