package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viajanteDTO.ViajanteCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/viajantes")
public class ViajanteController {

    @Autowired
    private ViajanteService viajanteService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ProvinciaDistritoService provinciaDistritoService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private DistritoService distritoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private CargaService cargaService;
    public ResponseEntity<ResponseAPI> criarViajante(@RequestBody  ViajanteCriarDTO dto){

        Genero genero= generoService.buscarPorId(dto.getIdGenero());
        if(genero==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Genero nao encontrado!", null));
        }
        Provincia provincia= provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        if(provincia==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Provincia nao encontrada!", null));
        }
        Distrito distrito= distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
        if(distrito==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Distrito nao encontrado!", null));
        }
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao= tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
        if(tipoDocumentoIdentificacao==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de documento nao encontrado!", null));
        }
        DocumentoIdentifiacacao documentoIdentifiacacao=documentoIdentificacaoService.buscarPorNumeroDocumento(dto.getCodigoIdentificacao());
        DocumentoIdentifiacacao novoDocmento=null;
        if(documentoIdentifiacacao==null){
            try{
                documentoIdentificacaoService.criar(new DocumentoIdentifiacacao(dto,tipoDocumentoIdentificacao));
            } catch (Exception e) {
                return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
            }
        } else {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, documento ja existe!", null));
        }
        Contacto contacto= contactoService.buscarContactoPorMsisdn(dto.getMsisdn());
        Contacto novoContacto=null;
        if(contacto==null) {

            try {
                contactoService.criar(new Contacto(dto));
            } catch (Exception e) {
                return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
            }
        }else{
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, contacto ja existe!", null));

            }
        Carga carga=cargaService.buscarCargaPorCodigo(dto.)
        try{
            viajanteService.criar(new Viajante(dto, genero, ))
        }

        }



    


}
