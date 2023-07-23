package com.sgvcore.Controllers;

import com.sgvcore.DTOs.viaturaDTOs.ViacturaAssociarMotoristaDTO;
import com.sgvcore.DTOs.viaturaDTOs.ViaturaCriarDTO;
import com.sgvcore.Model.*;
import com.sgvcore.exceptions.ContentAlreadyExists;
import com.sgvcore.exceptions.ModelNotFound;
import com.sgvcore.exceptions.NotOwner;
import com.sgvcore.sevices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RequestMapping("/api/viaturas")
@RestController
public class ViaturaController {

    @Autowired
    private ViaturaService viaturaService;
    @Autowired
    private RotaService rotaService;
    @Autowired
    private ProprietarioService proprietarioService;
    @Autowired
    private AssociacaoService associacaoService;
    @Autowired
    private AssociacaoRotaService asociacaoRotaService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private DocumentoIdentificacaoService documentoIdentificacaoService;
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private MotoristaViacturaService motoristaViacturaService;
    @Autowired
    private MotoristaService motoristaService;

    @PostMapping("/adicionar")
    public ResponseEntity<ResponseAPI> criarViacturas(@RequestBody @Valid ViaturaCriarDTO viaturaCriarDTO) throws NoSuchAlgorithmException, NotOwner, ModelNotFound, ContentAlreadyExists {
        viaturaService.criar(viaturaCriarDTO);
        return ResponseEntity.status(201).body(new ResponseAPI(true, "201", "Viatura criada com sucesso!", null));
    }

    @PostMapping("/associar-motorista")
    public ResponseEntity<ResponseAPI> associarViacturaMotorista(@RequestBody @Valid ViacturaAssociarMotoristaDTO dto) {
        Proprietario proprietario = proprietarioService.buscarPorCodigo(dto.getCodigoProprietario());
        if (proprietario == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Proprietario nao encontrado!", null));
        }
        Associacao associacao = associacaoService.buscarPorCodigo(dto.getCodigoAssociacao());
        if (associacao == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Associacao nao encontrada!", null));
        }
        Rota rota = asociacaoRotaService.buscarRotasPeloCodigoDaAssociacaoEAssociacao(associacao, dto.getCodigoRota());
        if (rota == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Rota nao encontrada ou nao pertence a associacao!", null));
        }
        Genero genero = generoService.buscarPorId(dto.getIdGenero());
        if (genero == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Gnero nao existe!", null));
        }
        Provincia provincia = provinciaService.buscarProvinciaporCodigo(dto.getCodigoProvincia());
        if (provincia == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Provincia nao encontrada!", null));
        }
        TipoDocumentoIdentificacao tipoDocumentoIdentificacao = tipoDocumentoService.buscarTipoDocumentoporId(dto.getTipoDocumento());
        if (tipoDocumentoIdentificacao == null) {
            return ResponseEntity.status(404).body(new ResponseAPI(false, "404", "Tipo de documento nao encontrado!", null));
        }
        DocumentoIdentifiacacao documentoIdentifiacacao = documentoIdentificacaoService.buscarPorNumeroDocumento(dto.getNumeroDocumento());
        DocumentoIdentifiacacao novoDocumento;
        if (documentoIdentifiacacao != null) {
            return ResponseEntity.status(422).body(new ResponseAPI(false, "422", "documento ja existe!", null));
        }
        try {
            novoDocumento = new DocumentoIdentifiacacao(dto, tipoDocumentoIdentificacao);
            documentoIdentificacaoService.criar(novoDocumento);
            Motorista motorista = new Motorista(dto, novoDocumento, genero, provincia);
            motoristaService.cirar(motorista);
            Viatura viatura = new Viatura(dto, rota, proprietario, associacao);
            viaturaService.criar(viatura);
            MotoristaViactura novoMotoristaViatura = new MotoristaViactura(motorista, viatura);
            motoristaViacturaService.criar(novoMotoristaViatura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(new ResponseAPI(false, "500", "Erro interno de servidor!", null));
        }
        return ResponseEntity.status(201).body(new ResponseAPI(true, "200", "Viactura e motorista Cadastrados com sucesso!", null));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> listar() {
        return ResponseEntity.status(200).body(new ResponseAPI(true, "200", "Viaturas do sistema!", viaturaService.listarViacturas()));
    }

}
