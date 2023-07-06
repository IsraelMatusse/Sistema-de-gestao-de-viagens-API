package com.sgvcore.Controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.sgvcore.DTOs.viagemDTO.ViagemAssociarViajanteDTO;
import com.sgvcore.DTOs.viagemDTO.ViagemCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viagem criada com sucesso!", null));
    }

    @PostMapping("/associar-viajante")
    public ResponseEntity<ResponseAPI> associarViajante(@RequestBody ViagemAssociarViajanteDTO dto) {
        // Realize as validações de existência
        Viagem viagem = viagemService.buscarPorCodigo(dto.getCodigoViagem());
        if (viagem == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Viagem não encontrada!", null));
        }
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        if (genero == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Gênero não encontrado!", null));
        }
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        if (provincia == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Província não encontrada!", null));
        }
        Distrito distrito = distritoService.buscarDistritoPorCodigoEProvincia(dto.getCodigoDistrito(), provincia);
        if (distrito == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Distrito não encontrado!", null));
        }
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getIdTipoDocumento());
        if (tipoDocumentoIdentificacao == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de documento não encontrado!", null));
        }

        // Verifique a não existência dos objetos antes de criá-los
        DocumentoIdentifiacacao documentoIdentifiacacao = documentoIdentificacaoService.buscarPorNumeroDocumento(dto.getNumeroDocumento());
        if (documentoIdentifiacacao != null) {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Documento já existe!", null));
        }
        Contacto contacto = contactoService.buscarContactoPorMsisdn(dto.getMsisdn());
        if (contacto != null) {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Contato já existe!", null));
        }
        Carga carga = cargaService.buscarCargaPorDesignacao(dto.getDesignacao());
        if (carga != null) {
            return ResponseEntity.status(409).body(new ResponseAPI(false, "409", "Erro, Carga já existe!", null));
        }

        // Crie e salve os objetos
        try {
            DocumentoIdentifiacacao novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
            documentoIdentificacaoService.criar(novoDocumento);

            Contacto novoContacto = new Contacto(dto);
            contactoService.criar(novoContacto);

            Carga novaCarga = new Carga(dto);
            cargaService.criar(novaCarga);

            Viajante viajante = new Viajante(dto, genero, novaCarga, novoDocumento, provincia, distrito, novoContacto);
            viajanteService.criar(viajante);

            ViagemViajante viagemViajante = new ViagemViajante(viagem, viajante);
            viagemViajanteService.crir(viagemViajante);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno do servidor", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viajante associado com sucesso!", null));
    }

    @GetMapping("/{codigo_viagem}/viajantes")
    public ResponseEntity<ResponseAPI> listarViajantesPorCodigoViagem(@PathVariable(value = "codigo_viagem") String codigoViagem){
        Viagem viagem=viagemService.buscarPorCodigo(codigoViagem);
        if(viagem==null){
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Viagem não encontrada!", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Lista de viajantes!", viagemViajanteService.listarViajantesDeUmaViagem(viagem)));
    }
}

