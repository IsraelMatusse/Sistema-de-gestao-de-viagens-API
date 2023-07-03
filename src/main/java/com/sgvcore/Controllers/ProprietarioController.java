package com.sgvcore.Controllers;

import com.sgvcore.DTOs.proprietarioDTOs.ProprietarioCriarDTO;
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
@RequestMapping("/api/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoProprietarioService tipoProprietarioService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoService distritoService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarProprietario(@RequestBody  ProprietarioCriarDTO proprietarioCriarDTO) throws NoSuchAlgorithmException {
        Genero genero = generoService.buscarPorId(proprietarioCriarDTO.getIdGenero());
        if (genero == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Genero nao enocntrado!", null));
        }
        TipoProprietario tipoProprietario = tipoProprietarioService.buscarPorCodigo(proprietarioCriarDTO.getCodigoTipoProprietario());
        if (tipoProprietario == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de proprietario nao enocntrado!", null));
        }
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(proprietarioCriarDTO.getCodigoProvincia());
        if (provincia == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Provincia nao enocntrada!", null));
        }
        Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(proprietarioCriarDTO.getCodigoDistrito(), provincia);
        if (distrito == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Documento nao enocntrada!", null));
        }
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarPorCodigo(proprietarioCriarDTO.getCodigoTipoDocumento());
        System.out.println(proprietarioCriarDTO.getCodigoTipoDocumento());
        if (tipoDocumentoIdentificacao==null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de Documento nao enocntrado!", null));
        }
        System.out.println(proprietarioCriarDTO.getCodigoTipoDocumento());
        Contacto contacto = contactoService.buscarContactoPorMsisdn(proprietarioCriarDTO.getMsidsn());
        Contacto novoContacto = null;
        if (contacto == null) {
            try {
                novoContacto = contactoService.criar(new Contacto(proprietarioCriarDTO));
            } catch (Exception e) {
                return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno do servidor", null));
            }
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Contacto ja existe!", null));
        }
        DocumentoIdentifiacacao documentoIdentifiacacao = documentoIdentificacaoService.buscarPorNumeroDocumento(proprietarioCriarDTO.getNumeroDocumento());
        DocumentoIdentifiacacao novoDocumento = null;
        if (documentoIdentifiacacao == null) {
            try {
                novoDocumento = documentoIdentificacaoService.criar(new DocumentoIdentifiacacao(proprietarioCriarDTO, tipoDocumentoIdentificacao));
            } catch (Exception e) {
                return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno do servidor", null));
            }
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Documento ja existe!", null));
        }
        try {
            Proprietario novoProprietario = new Proprietario(proprietarioCriarDTO, genero, novoContacto, provincia, tipoProprietario, novoDocumento, distrito);
            proprietarioService.criar(novoProprietario);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno do servidor", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(false, "200", "Proprietario cadastrado com sucesso", null));
    }
}
